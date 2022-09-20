package com.base.saas.manager.service;/**
 * Created by win7 on 2018/6/11.
 */

import com.base.saas.manager.model.SysModuleDetail;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @系统模块权限分配@
 * Create on : 2018年06月11日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface SysModuleDetailService {

    /**
     *根据 sysCode查询模块相关的菜单信息
     * @param sysCode
     * @return
     */
    List<Map> getModuleMenuListBySysCode(String sysCode)throws Exception;


    /***
     * 获取所有菜单
     * @param moduleDetail
     * @return
     */
    List<SysModuleDetail> getAllMenuDetailList(SysModuleDetail moduleDetail)throws Exception;


    SysModuleDetail getMenuById(String id)throws Exception;


    List<Map> getAllMenuTree(String sysCode)throws Exception;


    List<SysModuleDetail> getOneMenu(String flag, String sysCode)throws Exception;

    Map checkMenuChildStatus(String id, String flag)throws Exception;

    Map addMenu(SysModuleDetail moduleDetail)throws Exception;

    Map editMenu(SysModuleDetail moduleDetail)throws Exception;

    Map updateMenuStatus(SysModuleDetail moduleDetail)throws Exception;
}
