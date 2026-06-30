/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.test.service.impl;

import java.sql.Array;

import com.lealone.test.orm.generated.User;
import com.lealone.test.service.GeneratedModelTestSupport;
import com.lealone.test.service.generated.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public Long add(User user) {
        GeneratedModelTestSupport.prepareModelTables();
        return user.insert();
    }

    @Override
    public User find(String name) {
        GeneratedModelTestSupport.prepareModelTables();
        return User.dao.where().name.eq(name).findOne();
    }

    @Override
    public Integer update(User user) {
        GeneratedModelTestSupport.prepareModelTables();
        return user.update();
    }

    @Override
    public Integer delete(String name) {
        GeneratedModelTestSupport.prepareModelTables();
        return User.dao.where().name.eq(name).delete();
    }

    @Override
    public Array getList() {
        GeneratedModelTestSupport.prepareModelTables();
        return User.dao.findArray();
    }
}
