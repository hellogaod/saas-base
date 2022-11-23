package com.base.saas.manage.shiro;

import com.base.saas.userinfo.UserContextUtil;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月22日
 * Copyright (C)
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    public UserRealm() {
        setName("UserRealm");
    }

    private PermissionResolver permissionResolver;

    @Override
    public PermissionResolver getPermissionResolver() {
        return permissionResolver;
    }

    @Override
    public void setPermissionResolver(PermissionResolver permissionResolver) {
        this.permissionResolver = permissionResolver;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Set<String> permissions = Sets.newHashSet(UserContextUtil.getUserInfo().getPermissionList());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            UsernamePasswordToken upt = (UsernamePasswordToken) token;
            String account = upt.getUsername();
            String password = String.valueOf(upt.getPassword());
            // controller里已做过密码校验
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, password, ByteSource.Util.bytes(account), getName());
            return info;
        } catch (Exception ex) {
            logger.error("登录出现异常", ex);
            throw new AuthenticationException(ex);
        }
    }
}





