package com.ls.bootdemo.dao;

import com.ls.bootdemo.entity.Patient;

import java.util.List;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

    List<Patient> selectAll();

    List<Patient> selectAllByName(String name);
}