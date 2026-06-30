/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;

import com.lealone.db.Constants;

//标识它的子类是进行单元测试的
public class UnitTestBase extends TestBase implements TestBase.SqlExecutor, TestBase.EmbeddedTest {

    public UnitTestBase() {
    }

    private Connection getConn() throws SQLException {
        return DriverManager.getConnection(getURL());
    }

    @Override
    public void execute(String sql) {
        try (Connection conn = getConn(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(String sql) {
        try (Connection conn = getConn(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void explain(String sql) {
        try (Connection conn = getConn(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("EXPLAIN " + sql);
            if (rs.next()) {
                System.out.println();
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUpBefore() {
        useEmbeddedInMemoryUrl();
    }

    protected void useEmbeddedInMemoryUrl() {
        setEmbedded(true);
        setInMemory(true);
        System.setProperty(Constants.JDBC_URL_KEY, getURL());
    }

    @After
    public void tearDownAfter() {
        System.clearProperty(Constants.JDBC_URL_KEY);
    }

    public void runTest() {
        runTest(true);
    }

    public void runTest(boolean isEmbeddedMemoryMode) {
        if (isEmbeddedMemoryMode) {
            useEmbeddedInMemoryUrl();
        } else {
            System.setProperty(Constants.JDBC_URL_KEY, getURL());
        }
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.clearProperty(Constants.JDBC_URL_KEY);
        }
    }

    protected void test() throws Exception {
        // do nothing
    }
}
