package com.huotu.demo.glassfish1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author CJ
 */
public class Loader
        extends AbstractAnnotationConfigDispatcherServletInitializer implements ServletContainerInitializer
{

    @Override
    protected String[] getServletMappings() {
        ServletContainerInitializer servletContainerInitializer;
        PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor;
        JndiObjectFactoryBean bean;
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
        return new String[]{"/"};
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ConfigLoader.class};
//        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
//        return new Class<?>[]{ConfigLoader.class};
    }

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println(ctx);
    }

    @Configuration
    @ComponentScan("com.huotu.demo.glassfish1.config")
    public static class ConfigLoader {

    }
}
