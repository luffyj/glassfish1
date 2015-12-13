package com.huotu.demo.glassfish1.service;

import com.huotu.demo.glassfish1.TestBase;
import com.huotu.demo.glassfish1.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManagerFactory;

/**
 * @author CJ
 */
public class DemoServiceTest extends TestBase {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired(required = false)
    private UserRepository userRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testInit() throws Exception {
        System.out.println(applicationContext.getBean(UserRepository.class));
    }
}