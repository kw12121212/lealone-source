/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test.service.impl;

import com.lealone.test.orm.generated.User;
import com.lealone.test.service.GeneratedModelTestSupport;

// 动态创建ServiceExecutor
public class DynamicExecutorServiceImpl {

    public Long add(User user) {
        GeneratedModelTestSupport.prepareModelTables();
        return user.insert();
    }

    public Integer delete(String name) {
        GeneratedModelTestSupport.prepareModelTables();
        return User.dao.where().name.eq(name).delete();
    }

    public User find(String name) {
        GeneratedModelTestSupport.prepareModelTables();
        return User.dao.where().name.eq(name).findOne();
    }
}
