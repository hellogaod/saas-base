package com.base.saas.manager.service;


import com.base.saas.manager.model.SysEnterprise;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface SysEnterpriseService {
    //企业注册
    Map addSysEnterprise(Map map) throws Exception;

    //修改企业
    Map updateSysEnterprise(Map map)throws Exception;

    //获取列表
    List getSysEnterpriseList(String companyName)throws Exception;

    //修改回显数据
    SysEnterprise getEnterpriseByCompanyCode(String companyCode)throws Exception;

    //启用，停用
    Map updateStatus(Map map)throws  Exception;


}
