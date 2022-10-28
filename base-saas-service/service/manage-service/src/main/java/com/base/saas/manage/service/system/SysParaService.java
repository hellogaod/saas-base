package com.base.saas.manage.service.system;


import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年07月16日
 * Copyright (C)
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public interface SysParaService {

    List<Map> getSysParaByCompanyCode(String companyCode)throws Exception;


    Map saveSysPara(Map map)throws Exception;


    String getParaByCompanyAndKey(String companyCode, String key)throws Exception;

}
