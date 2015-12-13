package com.huotu.demo.glassfish1.config;

import org.luffy.lib.libspring.logging.LoggingConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author CJ
 */
@Configuration
@DependsOn("entityManagerFactory")
@EnableJpaRepositories("com.huotu.demo.glassfish1.repository")
@Import(LoggingConfig.class)
@ComponentScan({
        "com.huotu.demo.glassfish1.service"
        ,"com.huotu.demo.glassfish1.controller"
})
@EnableWebMvc
public class RootConfig {

}
