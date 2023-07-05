package com.wf.realm;

import com.wf.service.impl.SecurityServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class DefinitionRealm extends AuthorizingRealm {
    /**
     * 认证方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取登录名

        return null;
    }

    /**
     *  鉴权方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取登录名
        String loginName = (String) authenticationToken.getPrincipal();
        SecurityServiceImpl securityService = new SecurityServiceImpl();
        String password = securityService.findPasswordByLoginName(loginName);
        if ("".equals(password)){
            throw new UnknownAccountException("账户不存在");
        }
        return new SimpleAuthenticationInfo(loginName,password,getName());
    }
}
