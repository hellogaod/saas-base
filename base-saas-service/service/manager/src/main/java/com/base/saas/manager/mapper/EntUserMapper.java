package com.base.saas.manager.mapper;

import com.base.saas.manager.model.EntUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface EntUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(EntUser record);

    int insertSelective(EntUser record);

    EntUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(EntUser record);

    int updateByPrimaryKey(EntUser record);

    Map getUserByCompanyCode(@Param("companyCode") String companyCode);
}