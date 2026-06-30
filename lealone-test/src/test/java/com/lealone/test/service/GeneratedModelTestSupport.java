/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.lealone.db.Constants;
import com.lealone.test.TestBase.SqlExecutor;
import com.lealone.test.orm.SqlScript;

public class GeneratedModelTestSupport {

    private static final String MODEL_DATABASE_NAME = "service_model_test";
    private static volatile boolean modelTablesCreated;

    private GeneratedModelTestSupport() {
    }

    public static void useModelUrl() {
        System.setProperty(Constants.JDBC_URL_KEY, Constants.getEmbedUrl(MODEL_DATABASE_NAME));
    }

    public static synchronized void createModelTables() {
        String oldUrl = System.getProperty(Constants.JDBC_URL_KEY);
        useModelUrl();
        try {
            createModelTables0();
            modelTablesCreated = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (oldUrl != null)
                System.setProperty(Constants.JDBC_URL_KEY, oldUrl);
            else
                System.clearProperty(Constants.JDBC_URL_KEY);
        }
    }

    public static synchronized void prepareModelTables() {
        useModelUrl();
        if (modelTablesCreated)
            return;
        try {
            createModelTables0();
            modelTablesCreated = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void createModelTables0() throws Exception {
        try (Connection conn = DriverManager.getConnection(Constants.getEmbedUrl(MODEL_DATABASE_NAME));
                Statement stmt = conn.createStatement()) {
            SqlExecutor executor = sql -> {
                try {
                    stmt.executeUpdate(sql);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
            SqlScript.createUserTable(executor);
            SqlScript.createAllModelPropertyTable(executor);
        }
    }
}
