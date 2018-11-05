package com.ls.bootdemo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ls.bootdemo.dao.PatientMapper;
import com.ls.bootdemo.entity.Patient;
import com.ls.bootdemo.service.impl.IPatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/10/18 0018 23:53
 * 4
 */
@Slf4j
@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Patient findById(Integer id) {
        return null;
    }

    @Override
    public List<Patient> find() {
            log.info("查全部");
            return patientMapper.selectAll();
    }

    @Override
    public void insert(Patient patient) {
        patientMapper.insert(patient);
    }

    @Override
    public void delete(Integer id) {
        patientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Patient patient) {
        patientMapper.updateByPrimaryKey(patient);
    }
}
