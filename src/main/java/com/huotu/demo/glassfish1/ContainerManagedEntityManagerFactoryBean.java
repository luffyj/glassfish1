package com.huotu.demo.glassfish1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 * @author CJ
 */
public class ContainerManagedEntityManagerFactoryBean extends AbstractEntityManagerFactoryBean {

    private static final Log log = LogFactory.getLog(ContainerManagedEntityManagerFactoryBean.class);

    private EntityManagerFactory jndiResource;


    @Override
    protected EntityManagerFactory createNativeEntityManagerFactory() throws PersistenceException {
        log.info("Using ContainerManagedEntityManagerFactory: " +jndiResource );
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
}
