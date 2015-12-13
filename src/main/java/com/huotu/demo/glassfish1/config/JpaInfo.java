package com.huotu.demo.glassfish1.config;

import org.springframework.stereotype.Component;

/**
 * @author CJ
 */
@Component
public class JpaInfo {
    public String[] getEntityPackagesToScan() {
        return new String[]{"com.huotu.demo.glassfish1.entity1", "com.huotu.demo.glassfish1.entity2"};
    }
}
