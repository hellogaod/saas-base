package com.base.saas.manage.service.system;


import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.system.SysEnterprise;

import java.util.List;

/**
 * Title :
 * Description :企业端
 */
public interface SysEnterpriseService {
    //获取列表
    List getSysEnterpriseList(String companyName) throws Exception;

    //企业注册
    ReturnMap addSysEnterprise(SysEnterprise sysEnterprise) throws Exception;

    //启用，停用
    ReturnMap updateStatus(SysEnterprise map) throws Exception;

    //根据companyCode获取企业信息
    SysEnterprise getEnterpriseByCompanyCode(String companyCode) throws Exception;

    //修改企业
    ReturnMap updateSysEnterprise(SysEnterprise sysEnterprise) throws Exception;

}
