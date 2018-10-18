package com.ls.bootdemo.service.impl;

import com.github.pagehelper.PageInfo;
import com.ls.bootdemo.common.ServerResponse;
import com.ls.bootdemo.entity.User;

import java.util.List;

public interface IUserService {

    ServerResponse<PageInfo> findAll(int pageNum,int pageSize);
    ServerResponse<PageInfo> findByName(String name,int pageNum,int pageSize);
    List<User> findByName(String name);
    User findById(Integer id);
}
