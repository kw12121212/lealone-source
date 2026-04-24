/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test.db.table;

import java.util.ArrayList;

import org.junit.Test;

import com.lealone.db.schema.Schema;
import com.lealone.db.table.InfoMetaTable;
import com.lealone.db.table.Table;
import com.lealone.test.db.DbObjectTestBase;

public class MetaTableTest extends DbObjectTestBase {
    @Test
    public void run() {
        String infoSchemaName = "INFORMATION_SCHEMA";
        Schema infoSchema = db.findSchema(session, infoSchemaName);
        ArrayList<Table> tables = infoSchema.getAllTablesAndViews();
        // In addition to virtual meta tables, INFORMATION_SCHEMA has two real system tables.
        assertEquals(InfoMetaTable.getMetaTableTypeCount() + 2, tables.size());

        for (Table table : tables) {
            printTable(infoSchemaName, table.getName());
        }

        String[] tableNames = { "TABLE_TYPES", "CATALOGS", "SETTINGS", "TABLES", "HELP" };
        for (String tableName : tableNames) {
            printTable(infoSchemaName, tableName);
        }
    }

    void printTable(String infoSchemaName, String tableName) {
        // p("table name: " + tableName);
        // p("============================");
        // sql = "select * from " + infoSchemaName + "." + tableName;
        // printResultSet(sql);
    }
}
