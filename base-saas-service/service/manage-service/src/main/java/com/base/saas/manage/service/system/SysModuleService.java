package com.base.saas.manage.service.system;


import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.system.SysModule;

import java.util.List;

/**
 * Title :
 * Description :系统模块
 */
public interface SysModuleService {

    /**
     * 查询数据列表
     *
     * @return
     */
    List<SysModule> getSysModuleList(int status, String moduleName) throws Exception;

    /**
     * 保存
     *
     * @param module
     * @return
     */
    ReturnMap saveSysModule(SysModule module) throws Exception;


    /**
     * 修改
     *
     * @param module
     * @return
     */
    ReturnMap updateSysModule(SysModule module) throws Exception;


    /**
     * 修改状态
     *
     * @param module
     * @return
     */
    ReturnMap updateSysModuleStatus(SysModule module) throws Exception;


    /**
     * 获取模块信息
     *
     * @param moduleId
     * @return
     */
    SysModule getModuleInfo(String moduleId) throws Exception;
}
