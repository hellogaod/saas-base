package com.base.saas.manage.service.system;


import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.system.SysOtherConfig;

import java.util.List;

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
public interface SysOtherConfigService {

    /**
     * 根据数据列表
     *
     * @return
     */
    List<SysOtherConfig> getOtherConfigList(int status, int type, String otherName) throws Exception;

    /**
     * 保存参数配置
     *
     * @param sysOtherConfig
     * @return
     */
    ReturnMap saveOtherConfig(SysOtherConfig sysOtherConfig) throws Exception;


    /**
     * 修改状态信息
     *
     * @param sysOtherConfig
     * @return
     */
    ReturnMap updateStatus(SysOtherConfig sysOtherConfig) throws Exception;

    /**
     * 修改参数配置
     *
     * @param sysOtherConfig
     * @return
     */
    ReturnMap updateOtherConfig(SysOtherConfig sysOtherConfig) throws Exception;


    /**
     * 查询一条参数配置，以及当前配置下的参数集合
     *
     * @param otherId
     * @return
     */
    SysOtherConfig getOtherConfInfo(String otherId) throws Exception;
}
