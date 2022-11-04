package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.domain.entity.enterprise.EntOrganization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title :
 * Description : 组织架构管理
 */
@Mapper
public interface EntOrganizationMapper {

    //插入一条组织架构
    int insertSelective(EntOrganization record);

    //更新一条组织架构信息
    int updateByPrimaryKeySelective(EntOrganization record);

    //查询所有数据
    List<EntOrganization> findList(@Param("companyCode") String companyCode,
                                   @Param("orgName") String orgName,
                                   @Param("orgCode") String orgCode,
                                   @Param("parentOrgId") String parentOrgId,
                                   @Param("orgType") int orgType,
                                   @Param("status") int status);

    //根据id关联查询父类
    EntOrganization findById(String orgId);

}