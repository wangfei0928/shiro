package com.example.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myController")
public class MyController {


    //跳转登录页面
    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("/userLogin")
    public String userLogin(String name, String pwd, HttpSession session) {
        //1. 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2. 封装请求数据到token对象中
        AuthenticationToken token = new UsernamePasswordToken(name, pwd);
        try {
            //3. 调用login方法进行登录认证
            subject.login(token);
            System.out.println("登录成功");
            session.setAttribute("user",name);
            return "main";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }
}
