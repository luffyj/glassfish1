package com.huotu.demo.glassfish1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * @author CJ
 */
@Configuration
@Profile("container")
@ImportResource("classpath:container.xml")
public class ContainerConfig {

}
