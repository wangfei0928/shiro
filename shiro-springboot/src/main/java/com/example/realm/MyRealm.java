package com.example.realm;

import com.example.entity.User;
import com.example.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        //1. 获取用户身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();
        //2. 调用业务层获取用户的角色信息
        List<String> roles = userService.getUserRoleInfo(principal);
        System.out.println("当前用户拥有的角色有："+roles);
        System.out.println("自定义授权方法");
        //3. 调用接口方法获取用户的权限信息
        List<String> permissions = userService.getUserPermissionInfo(roles);
        System.out.println("当前用户拥有的权限信息有:"+permissions);
        //1. 创建对象，用来封装当前登录用户的角色、权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //2. 存储相关角色
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        //3. 返回角色信息
        return info;
    }


    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1. 获取用户身份信息
        String name = authenticationToken.getPrincipal().toString();
        //2. 调用业务层获取用户信息(数据库表中存储的)
        User user = userService.getUserInfo(name);
        //3. 判断是否有无用户,如果有，将数据封装返回
        if (user != null){
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes("salt"),
                    name
            );
            return info;
        }
        return null;
    }
}
