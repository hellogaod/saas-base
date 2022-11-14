package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.domain.entity.enterprise.EntUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntUserMapper {

    /**
     * @Description: 获取用户列表
     * @return:
     */
    List<EntUser> getUserList(@Param("companyCode") String companyCode,
                              @Param("status") int status,
                              @Param("account") String account,
                              @Param("realName") String realName,
                              @Param("tel") String tel);

    //插入一条用户账号信息
    int insertSelective(EntUser record);

    //根据realname查找roleId
    String getRoleIdByRealName(@Param("realName") String realName);

    //修改用户信息
    int updateByPrimaryKeySelective(EntUser record);

    //根据用户id(或企业编号和账户)获取用户信息
    EntUser selectByPrimaryKey(@Param("userId")String userId, @Param("companyCode") String companyCode, @Param("account") String account);

    //修改查询账号是否已存在
    List<String> getUserByUpdate(EntUser user);

}