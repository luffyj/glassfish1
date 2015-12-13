package com.huotu.demo.glassfish1;

import libspringtest.SpringWebTest;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author CJ
 */
@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Loader.ConfigLoader.class, TestBase.TestConfig.class
//        , DeploySecurityConfig.class
//        , LibJpaConfig.class, LibMVCConfig.class
//        , MVCConfig.class
})
public abstract class TestBase extends SpringWebTest {
    @Configuration
    @Profile("!container")
    @ImportResource("classpath:test.xml")
    public static class TestConfig {

    }
}
