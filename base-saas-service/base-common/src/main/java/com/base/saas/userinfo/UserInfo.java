package com.base.saas.userinfo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description :存储于redis的用户信息
 */
@Data
@Slf4j
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 7704782905876663335L;

    private String userId;

    private String account;

    private String companyCode;

    private String companyName;

    private String orgId;

    private String realName;

    private String tel;

    /**
     * 登录用户的类别；
     * 1：运营平台用户
     * 2:企业用户
     */
    private int userType;

    /**
     * 用户拥有权限的菜单列表
     */
    private List<String> permissionList;

    /**
     * 用户拥有权限的菜单列表的数据权限，数据查看范围和是否脱敏展示
     */
    private Map<String, Map<String, String>> dataPermission;

    /**
     * 登录令牌
     */
    private String token;

}
