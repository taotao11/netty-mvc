package com.netty.controller;

import com.netty.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    public LoginService loginService;

    @RequestMapping("/login")
    public Map login(String name,String pwd){
       return loginService.login(name,pwd);
    }
}
