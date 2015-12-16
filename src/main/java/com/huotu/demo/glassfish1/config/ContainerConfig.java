package com.huotu.demo.glassfish1.config;

import com.huotu.atomikos.JtaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos/
 * https://github.com/fabiomaffioletti/mul-at
 *
 * @author CJ
 */
@Configuration
@Profile("container")
@ImportResource("classpath:container.xml")
@Import(JtaConfig.class)
public class ContainerConfig {


}
