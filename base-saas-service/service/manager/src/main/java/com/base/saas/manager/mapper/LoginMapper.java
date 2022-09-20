package com.base.saas.manager.mapper;

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
public interface LoginMapper {
    Map getUser(String account);

    void lockUser(@Param("userId") String userId, @Param("status") String status);

    void clearErrCount(@Param("userId") String userId);

    List<Map> getPermissions(String userId);

    //修改密码
    int updatePwd(Map map);

    //查询用户角色信息
    Map getUserRoleInfo(String userId);


    int updateLoginInfo(@Param("userId") String userId, @Param("remoteAddr") String remoteAddr);
}
