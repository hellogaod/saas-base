package com.base.saas.manage.service.enterprise;

import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntOrganization;

import java.util.List;

/**
 * Title :
 * Description : @组织架构管理@
 */
public interface EntOrganizationService {

    //查询所有数据
    List<EntOrganization> getOrgList(String companyCode,
                                     String orgName,
                                     String orgCode,
                                     String parentOrgId,
                                     int orgType,
                                     int status) throws Exception;

    //新增
    ReturnMap saveOrgnization(EntOrganization organization) throws Exception;

    //根据id查询
    EntOrganization getOrgById(String orgId) throws Exception;

    //修改
    ReturnMap updateOrgnization(EntOrganization organization) throws Exception;

    //修改状态
    ReturnMap updateOrgStatus(EntOrganization org) throws Exception;

}
