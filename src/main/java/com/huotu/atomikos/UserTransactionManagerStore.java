package com.huotu.atomikos;

import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * @author CJ
 */
public class UserTransactionManagerStore {
    private static UserTransactionManagerStore ourInstance = new UserTransactionManagerStore();

    public static UserTransactionManagerStore getInstance() {
        return ourInstance;
    }

    private UserTransactionManagerStore() {
    }

    private UserTransactionManager manager;

    public UserTransactionManager requestUserTransactionManager() {
        if (manager == null) {
            manager = new UserTransactionManager();
            return manager;
        } else {
            UserTransactionManager userTransactionManager = manager;
            manager = null;
            return userTransactionManager;
        }
    }
}
