package com.huotu.atomikos;

import org.eclipse.persistence.sessions.DatabaseSession;

/**
 * @author CJ
 */
public class AtomikosPlatform extends com.atomikos.eclipselink.platform.AtomikosPlatform {
    public AtomikosPlatform(DatabaseSession newDatabaseSession) {
        super(newDatabaseSession);
    }

    @Override
    public Class<?> getExternalTransactionControllerClass() {
        return StoredJTATransactionController.class;
    }
}
