package com.huotu.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Jta事务的配置者,
 * 需要添加eclipselink配置
 * eclipselink.target-server com.huotu.atomikos.AtomikosPlatform
 *
 * @author CJ
 */
@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
public class JtaConfig {

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        //采用一次保存 一次放弃的原则处理.
        UserTransactionManager userTransactionManager = UserTransactionManagerStore.getInstance().requestUserTransactionManager();
        userTransactionManager.setForceShutdown(true);

//        AtomikosJtaPlatform.transactionManager = userTransactionManager;

        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction", "atomikosTransactionManager"})
    @Autowired
    public PlatformTransactionManager transactionManager(UserTransaction userTransaction
            , TransactionManager atomikosTransactionManager) throws Throwable {
//        UserTransaction userTransaction = userTransaction();

//        AtomikosJtaPlatform.transaction = userTransaction;

//        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }
}
