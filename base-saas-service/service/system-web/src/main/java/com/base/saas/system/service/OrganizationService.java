package com.base.saas.system.service;/**
 * Created by win7 on 2018/5/24.
 */



import com.base.saas.system.model.Organization;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @组织架构管理@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface OrganizationService {
    //查询所有数据
    List<Organization> getOrgList(Map map)throws Exception;

    //新增
    Map saveOrgnization(Organization organization)throws Exception;

    //根据id查询
    Map getOrgById(String orgId)throws Exception;

    //修改
    Map updateOrgnization(Organization organization)throws Exception;

    //获取组织及下级
    List<Organization> getOrgListByOrgId(String orgId, String companyCode)throws Exception;

    //修改状态
    Map updateOrgStatus(Organization org)throws Exception;

    //获取类型为公司的组织机构
    List<Organization> getOrgListByMap(Map map)throws Exception;

}
