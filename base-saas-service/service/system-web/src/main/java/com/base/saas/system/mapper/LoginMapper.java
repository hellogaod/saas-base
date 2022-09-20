package com.base.saas.system.mapper;

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
    Map getComUser(@Param("account") String account, @Param("companyCode") String companyCode);

    void lockComUser(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("status") String status);

    void clearComErrCount(@Param("userId") String userId, @Param("companyCode") String companyCode);

    List<Map> getComPermissions(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("sysCode") String sysCode);

    //修改密码
    int updateComPwd(Map map);

    //查询用户角色信息
    Map getComUserRoleInfo(@Param("userId") String userId, @Param("companyCode") String companyCode);


    int updateComLoginInfo(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("remoteAddr") String remoteAddr);
}
