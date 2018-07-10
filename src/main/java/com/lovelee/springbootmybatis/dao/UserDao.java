package com.lovelee.springbootmybatis.dao;

import com.lovelee.springbootmybatis.model.UserDomain;

import java.util.List;

public interface UserDao {
    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
