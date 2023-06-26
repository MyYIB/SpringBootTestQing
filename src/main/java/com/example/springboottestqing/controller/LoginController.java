package com.example.springboottestqing.controller;


import com.example.springboottestqing.entity.User;

import com.example.springboottestqing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringBootTetstQing
 * @description: 登录
 * @author：xc
 */
@RestController
@RequestMapping(value = "/login",method = RequestMethod.POST)
public class LoginController {

    @Autowired
    private UserService userService;

//    @PostMapping("login")
//    public CommonResp login(LoginRequest req){
//
//    }
}
