package com.example.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String userLogin(String name, String pwd, HttpSession session, @RequestParam(defaultValue = "false")boolean rememberMe) {
        //1. 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2. 封装请求数据到token对象中
        AuthenticationToken token = new UsernamePasswordToken(name, pwd,rememberMe);
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

    //登录认证验证 rememberMe
    @GetMapping("/userLoginRm")
    public String userLogin(HttpSession session) {
        session.setAttribute("user","rememberMe");
        return "main";
    }

    //登录认证验证角色
    @RequiresRoles("admin")
    @GetMapping("/userLoginRoles")
    @ResponseBody
    public String userLoginRoles(){
        System.out.println("登录认证，验证角色");
        return "验证角色成功";
    }


    //登录验证权限
    @RequiresPermissions("user:delete")
    @GetMapping("/userPermissions")
    @ResponseBody
    public String userLoginPermissions(){
        System.out.println("登录认证验证权限");
        return "验证权限成功";
    }
}
