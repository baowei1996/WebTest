package com.saprj.dao;

import com.saprj.entity.User;

public interface UserMapper {
    User selectByPrimaryKey(int uid);

    void insert(User user);

    void insertSelective(User user);

    User selectByUsername(String username);
}
