package com.netty.service;

import java.util.Map;

public interface LoginService {
    public Map<String,String> login(String name,String pwd);
}
