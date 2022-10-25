package com.base.saas.manage.mapper.system;

import com.base.saas.manage.model.enterprise.EntMenu;
import com.base.saas.manage.model.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Mapper
public interface SysLoginMapper {

    //sysuser用户信息
    SysUser getUser(String account);

    //status不为2的情况下:1.errorcount错误次数+1；2. status状态值设置成给定status
    void lockUser(@Param("userId") String userId, @Param("status") String status);

    //设置状态 = 1，errorcount = 0
    void clearErrCount(@Param("userId") String userId);

    //系统管理员用户拥有的角色，该角色拥有的菜单权限
    List<EntMenu> getPermissions(String userId);

    //修改密码
    int updatePwd(@Param("userId") String userId, @Param("newPwd") String newPwd);

    //查询用户角色信息
    Map getUserRoleInfo(String userId);

    //更新最后一次登录ip地址
    int updateLoginInfo(@Param("userId") String userId, @Param("remoteAddr") String remoteAddr);


}
