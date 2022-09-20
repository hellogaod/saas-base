package com.base.saas.manager.mapper;

import com.base.saas.manager.model.EntOrganization;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntOrganizationMapper {
    int deleteByPrimaryKey(String orgId);

    int insert(EntOrganization record);

    int insertSelective(EntOrganization record);

    EntOrganization selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(EntOrganization record);

    int updateByPrimaryKeyWithBLOBs(EntOrganization record);

    int updateByPrimaryKey(EntOrganization record);
}