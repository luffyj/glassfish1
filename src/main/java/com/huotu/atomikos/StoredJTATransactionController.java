package com.huotu.atomikos;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.eclipse.persistence.transaction.JTATransactionController;

import javax.transaction.TransactionManager;

/**
 * @author CJ
 */
public class StoredJTATransactionController extends JTATransactionController {

    private UserTransactionManager userTransactionManager;

    public StoredJTATransactionController() {
        userTransactionManager = UserTransactionManagerStore.getInstance().requestUserTransactionManager();
    }

    @Override
    protected TransactionManager acquireTransactionManager() throws Exception {
        return userTransactionManager;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return userTransactionManager;
    }

    //    public StoredJTATransactionController(TransactionManager transactionManager) {
//        super(transactionManager);
//    }
}
