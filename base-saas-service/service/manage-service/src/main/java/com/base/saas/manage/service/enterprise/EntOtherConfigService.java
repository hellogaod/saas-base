package com.base.saas.manage.service.enterprise;


import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.system.SysOtherConfig;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 三方配置给企业端
 */
public interface EntOtherConfigService {

    List<SysOtherConfig> getConfigList(String companyCode);

    ReturnMap updateCompanyOtherConf(Map data) throws Exception;
}
