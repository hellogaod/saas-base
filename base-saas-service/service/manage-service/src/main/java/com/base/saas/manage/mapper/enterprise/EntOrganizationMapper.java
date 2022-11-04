package com.base.saas.manage.mapper.enterprise;


<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntOrganization;
=======
import com.base.saas.manage.model.enterprise.EntOrganization;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

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