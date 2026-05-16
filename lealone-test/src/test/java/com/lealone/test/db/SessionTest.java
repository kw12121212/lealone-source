/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test.db;

import org.junit.Test;

import com.lealone.db.session.ServerSession;
import com.lealone.sql.PreparedSQLStatement;

public class SessionTest extends DbTestBase {
    @Test
    public void run() {
        setInMemory(true);
        setEmbedded(true);
        // 删除LealoneDatabase的持久化元数据
        deleteFileRecursive(TEST_DIR);

        String url = getURL();
        ServerSession session = createServerSession(url);

        String sql = "CREATE TABLE IF NOT EXISTS SessionTest(f1 int, f2 int)";
        int fetchSize = 0;
        PreparedSQLStatement ps = session.prepareStatement(sql, fetchSize);
        p(ps.isQuery());
    }
}
