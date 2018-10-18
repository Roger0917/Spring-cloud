package com.ls.bootdemo.service.impl;

import com.github.pagehelper.PageInfo;
import com.ls.bootdemo.common.ServerResponse;
import com.ls.bootdemo.entity.Patient;
import com.ls.bootdemo.entity.User;

import java.util.List;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/10/18 0018 23:43
 * 4
 */
public interface IPatientService {

    //List<User> findByName(String name);
    Patient findById(Integer id);

    PageInfo find(String name,int pageNum, int pageSize);

    void insert(Patient patient);

    void delete(Integer id);

    void update(Patient patient);
}
