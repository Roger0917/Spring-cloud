package com.ls.bootdemo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ls.bootdemo.common.ServerResponse;
import com.ls.bootdemo.dao.UserMapper;
import com.ls.bootdemo.entity.User;
import com.ls.bootdemo.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<PageInfo> findAll(int pageNum,int pageSize) {
        List<User> list = userMapper.selectAll();
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> findByName(String name, int pageNum, int pageSize) {
        return null;
    }

    /*@Override
    public PageInfo findByName(String name, int pageNum, int pageSize) {
        List<User> list = userMapper.selectAllByName(name);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }*/

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
