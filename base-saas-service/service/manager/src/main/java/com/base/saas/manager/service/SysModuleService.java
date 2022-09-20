package com.base.saas.manager.service;


import com.base.saas.manager.model.SysModule;

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
public interface SysModuleService {
    //获取启用的系统模块
    List getEffectiveModule()throws Exception;

    /**
     * 查询数据列表
     * @param map
     * @return
     */
    List<SysModule> getSysModuleList(Map map)throws Exception;

    /**
     * 保存
     * @param module
     * @return
     */
    Map saveSysModule(SysModule module)throws Exception;


    /**
     *修改
     * @param module
     * @return
     */
    Map updateSysModule(SysModule module)throws Exception;


    /**
     * 修改状态
     * @param module
     * @return
     */
    Map updateSysModuleStatus(SysModule module)throws Exception;


    /**
     * 获取模块信息
     * @param sysCode
     * @return
     */
    SysModule getModuleInfo(String sysCode)throws Exception;
}
