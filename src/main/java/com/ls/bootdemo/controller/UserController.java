package com.ls.bootdemo.controller;

import com.github.pagehelper.PageInfo;
import com.ls.bootdemo.common.ServerResponse;
import com.ls.bootdemo.service.UserService;
import com.ls.bootdemo.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/pageall")
    public ServerResponse<PageInfo> listAll(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "3")int pageSize
                                        ){
        return iUserService.findAll(pageNum,pageSize);
    }

    @RequestMapping("/pagebyname")
    public ServerResponse<PageInfo> listByName(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
                                       @RequestParam(value = "name",defaultValue = "")String name
                                       ){
        return iUserService.findByName(name,pageNum,pageSize);
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
