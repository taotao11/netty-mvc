package com.netty.service.impl;

import com.netty.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    public Map<String, String> login(String name, String pwd) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("pwd",pwd);
        return map;
    }
}
