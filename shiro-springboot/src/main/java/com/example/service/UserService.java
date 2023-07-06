package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {

    //用户登录方法
    public User getUserInfo(String name);

    //根据用户查询角色信息
    List<String> getUserRoleInfo(String principal);

    List<String> getUserPermissionInfo(List<String> roles);
}
