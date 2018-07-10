package com.lovelee.springbootmybatis.service.user;

import com.github.pagehelper.PageInfo;
import com.lovelee.springbootmybatis.model.UserDomain;

public interface UserService {
    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
