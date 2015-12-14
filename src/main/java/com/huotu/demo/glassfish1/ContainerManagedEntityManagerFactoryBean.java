package com.huotu.demo.glassfish1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.ExtendedEntityManagerCreator;
import org.springframework.util.ClassUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author CJ
 */
public class ContainerManagedEntityManagerFactoryBean extends AbstractEntityManagerFactoryBean {

    private static final Log log = LogFactory.getLog(ContainerManagedEntityManagerFactoryBean.class);

    private EntityManagerFactory jndiResource;

    @Override
    protected EntityManagerFactory createEntityManagerFactoryProxy(EntityManagerFactory emf) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        if (this.getEntityManagerInterface() != null) {
            classes.add(this.getEntityManagerInterface());
        } else {
            classes.addAll(ClassUtils.getAllInterfacesForClassAsSet(emf.getClass(), this.getBeanClassLoader()));
        }
        classes.add(EntityManagerFactoryInfo.class);
        try {
            return (EntityManagerFactory) Proxy.newProxyInstance(
                    this.getBeanClassLoader(), classes.toArray(new Class<?>[classes.size()]),
                    new ManagedEntityManagerFactoryInvocationHandler(this));
        } catch (IllegalArgumentException ex) {
            if (this.getEntityManagerInterface() != null) {
                throw new IllegalStateException("EntityManagerFactory interface [" + this.getEntityManagerInterface() +
                        "] seems to conflict with Spring's EntityManagerFactoryInfo mixin - consider resetting the " +
                        "'entityManagerFactoryInterface' property to plain [javax.persistence.EntityManagerFactory]", ex);
            } else {
                throw new IllegalStateException("Conflicting EntityManagerFactory interfaces - " +
                        "consider specifying the 'jpaVendorAdapter' or 'entityManagerFactoryInterface' property " +
                        "to select a specific EntityManagerFactory interface to proceed with", ex);
            }
        }
    }

    @Override
    protected EntityManagerFactory createNativeEntityManagerFactory() throws PersistenceException {
        log.info("Using ContainerManagedEntityManagerFactory: " + jndiResource);
//        this.nativeEntityManagerFactory = jndiResource;
//        return createEntityManagerFactoryProxy(jndiResource);
        return jndiResource;
    }

    @Override
    public void destroy() {
        if (log.isInfoEnabled()) {
            log.info("for ContainerManagedEntityManagerFactory, close is ignoring.");
        }
    }

    public EntityManagerFactory getJndiResource() {
        return jndiResource;
    }

    public void setJndiResource(EntityManagerFactory jndiResource) {
        this.jndiResource = jndiResource;
    }

    private static class ManagedEntityManagerFactoryInvocationHandler implements InvocationHandler, Serializable {

        private final ContainerManagedEntityManagerFactoryBean entityManagerFactoryBean;

        public ManagedEntityManagerFactoryInvocationHandler(ContainerManagedEntityManagerFactoryBean emfb) {
            this.entityManagerFactoryBean = emfb;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                if (method.getName().equals("equals")) {
                    // Only consider equal when proxies are identical.
                    return (proxy == args[0]);
                } else if (method.getName().equals("hashCode")) {
                    // Use hashCode of EntityManagerFactory proxy.
                    return System.identityHashCode(proxy);
                } else if (method.getName().equals("unwrap")) {
                    // Handle JPA 2.1 unwrap method - could be a proxy match.
                    Class<?> targetClass = (Class<?>) args[0];
                    if (targetClass == null || targetClass.isInstance(proxy)) {
                        return proxy;
                    }
                }
                return this.entityManagerFactoryBean.invokeContainerManagedProxyMethod(method, args);
            } catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
    }

    private Object invokeContainerManagedProxyMethod(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (method.getDeclaringClass().isAssignableFrom(EntityManagerFactoryInfo.class)) {
            return method.invoke(this, args);
        } else if (method.getName().equals("createEntityManager") && args != null && args.length > 0 &&
                args[0] != null && args[0].getClass().isEnum() && "SYNCHRONIZED".equals(args[0].toString())) {
            // JPA 2.1's createEntityManager(SynchronizationType, Map)
            // Redirect to plain createEntityManager and add synchronization semantics through Spring proxy

            EntityManager rawEntityManager = (args.length > 1 ?
                    this.nativeEntityManagerFactory.createEntityManager((Map<?, ?>) args[1]) :
                    this.nativeEntityManagerFactory.createEntityManager());

            return ExtendedEntityManagerCreator.createContainerManagedEntityManager(rawEntityManager,this);
//            return ExtendedEntityManagerCreator.createApplicationManagedEntityManager(rawEntityManager, this, true);
        }

        // Standard delegation to the native factory, just post-processing EntityManager return values
        Object retVal = method.invoke(this.nativeEntityManagerFactory, args);
        if (retVal instanceof EntityManager) {
            // Any other createEntityManager variant - expecting non-synchronized semantics
            EntityManager rawEntityManager = (EntityManager) retVal;
            retVal = ExtendedEntityManagerCreator.createContainerManagedEntityManager(rawEntityManager, this);
        }
        return retVal;
    }
}
