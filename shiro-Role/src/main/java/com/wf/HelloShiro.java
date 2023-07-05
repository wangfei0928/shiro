package com.wf;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * shiro的第一个例子
 */

public class HelloShiro {

    @Test
    public void shiroLogin(){

        //导入INI配置创建工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //工厂构建安全管理器
        SecurityManager instance =  factory.getInstance();
        //使用工具生效安全管理器
        SecurityUtils.setSecurityManager(instance);
        //使用工具获得subject主体
        Subject subject = SecurityUtils.getSubject();
        //构建账户密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "z3");
        //使用subject主体去登陆
        subject.login(usernamePasswordToken);
        //打印登录信息
        System.out.println("登录结果："+subject.isAuthenticated());

        //判断角色
        boolean role1 = subject.hasRole("role1");
        System.out.println("是否有role1:"+role1);

        //判断权限
        boolean permitted = subject.isPermitted("user:insert");
        System.out.println("是否有insert权限："+permitted);
    }

}
