package com.ls.bootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ls.bootdemo.entity.Patient;
import com.ls.bootdemo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/10/19 0019 0:08
 * 4
 */
@RestController
@RequestMapping("/pat/")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("insert")
    public String insert(Patient patient){
        patientService.insert(patient);
        return "新增记录成功";
    }

    @RequestMapping("delete")
    public String delete(Integer id){
        patientService.delete(id);
        return "删除记录成功";
    }

    @RequestMapping("update")
    public String update(Patient patient){
        patientService.update(patient);
        return "更新记录成功";
    }

    @RequestMapping("findByPage")
    public String find(@RequestParam(value = "pageNumber",required = false)Integer pageNumber, @RequestParam(value="pageSize",required = true,defaultValue = "1")Integer pageSize,@RequestParam(value = "name",required = true,defaultValue = "")String name){
        List list= patientService.find();
        int total = list.size();
        PageHelper.startPage(pageNumber,pageSize);
        List pageInfo= patientService.find();
        JSONObject result = new JSONObject();
        result.put("rows",pageInfo);
        result.put("total",total);
        System.out.println(result.toJSONString());
        return result.toJSONString();


    }


}
