package com.wf.service.impl;

import com.wf.service.SecurityService;

/**
 * 模拟数据库，实现接口
 */
public class SecurityServiceImpl implements SecurityService {
    @Override
    public String findPasswordByLoginName(String loginName) {
        return "123";
    }
}
