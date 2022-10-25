package com.base.saas.manage.mapper;

import com.base.saas.manage.model.enterprise.EntUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface EntUserMapper {
    //获取当前企业管理员账号信息
    Map getUserByCompanyCode(@Param("companyCode") String companyCode, @Param("account") String account);

    //根据realname查找roleId
    String getRoleIdByRealName(@Param("realName") String realName);

    //插入一条用户账号信息
    int insertSelective(EntUser record);

    int deleteByPrimaryKey(String userId);

    EntUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(EntUser record);

    int updateByPrimaryKey(EntUser record);

}