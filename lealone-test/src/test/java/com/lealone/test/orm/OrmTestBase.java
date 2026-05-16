/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test.orm;

import org.junit.Before;
import com.lealone.db.Constants;
import com.lealone.test.UnitTestBase;

public abstract class OrmTestBase extends UnitTestBase {
    @Before
    @Override
    public void setUpBefore() {
        setEmbedded(true);
        setInMemory(true);
        // 删除LealoneDatabase的持久化元数据，
        // 避免前一个测试类在同一个目录下创建的数据库被恢复
        deleteFileRecursive(TEST_DIR);
        System.setProperty(Constants.JDBC_URL_KEY, getURL());
        SqlScript.createTables(this);
    }
}
