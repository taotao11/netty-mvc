package com.netty.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ConvertController
{
  @RequestMapping("/test")
 public String test(){
   return "success";
 }

 @ResponseBody
 @RequestMapping("/suc")
 public Map<String ,String> suc(@RequestParam("name") String name){
      Map<String,String> suc = new HashMap<String, String>();
     System.out.println(name);
      suc.put("name",name);

      return suc;
 }
}
