package com.base.saas.manager.service;

import java.util.List;

/**
 * Title :
 * Description :
 * Create on : 2018年06月11日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface EntModuleService {
    //获取企业的系统模块
    List getModuleByCompanyCode(String companyCode)throws Exception;
}
