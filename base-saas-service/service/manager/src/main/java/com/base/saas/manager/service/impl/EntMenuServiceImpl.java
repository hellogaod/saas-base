package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.EntMenuMapper;
import com.base.saas.manager.mapper.EntModuleMapper;
import com.base.saas.manager.mapper.SysModuleDetailMapper;
import com.base.saas.manager.model.EntMenu;
import com.base.saas.manager.model.EntModule;
import com.base.saas.manager.service.EntMenuService;
import com.base.saas.common.exception.TransactionRollBackException;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @企业菜单@
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class EntMenuServiceImpl implements EntMenuService {

    private static Logger logger = LoggerFactory.getLogger(EntMenuServiceImpl.class);

    @Autowired
    private SysModuleDetailMapper sysModuleDetailMapper;

    @Autowired
    private EntMenuMapper entMenuMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    /**
    * @Description:
    * @Param:  * @param sysCodeList系统模块编号  @param companyCode 企业编号
    * @return: int
    * @Date: 2018/6/8
    */

    @Transactional(rollbackFor = TransactionRollBackException.class)
    @Override
    public int addEntMenuAndEntModule(List<String> sysCodeList, String companyCode) {
        int entMenuNum = 0;
        try {
            //停用之前本企业的所有的模块
            entModuleMapper.updateEntModuleStatus(companyCode);
            //停用之前本企业的所有的菜单
            entMenuMapper.updateEntMenuStatus(companyCode);
            if (null != sysCodeList && sysCodeList.size() > 0) {
                //创建企业系统模块对象集合
                List<EntModule> entModuleList = new ArrayList<>();
                //创建企业菜单对象集合
                List<EntMenu> entMenuList = new ArrayList<>();
                int i = 0;
                for (String sysCode : sysCodeList) {
                    //通过sysCode查询平台系统模块对应的菜单信息以及模块信息
                    List<Map> sysModuleDetailList = sysModuleDetailMapper.getDetailAndModelListBySysCode(sysCode);

                    //遍历系统菜单信息
                    if (null != sysModuleDetailList && sysModuleDetailList.size() > 0) {
                        //获取每次查询的系统模块信息
                        Map sysModuleDetailMap = sysModuleDetailList.get(0);
                        String sysName = String.valueOf(sysModuleDetailMap.get("sysName"));
                        short status = Short.parseShort(String.valueOf(sysModuleDetailMap.get("moduleStatus")));
                        String remark = String.valueOf(sysModuleDetailMap.get("moduleRemark"));

                        //企业系统模块对象
                        EntModule entModule = new EntModule();
                        //企业系统模块的sys_code
                        String newCode = CreateIDUtil.getId();
                        //企业编号
                        entModule.setCompanyCode(companyCode);
                        //企业模块编号
                        entModule.setSysCode(newCode);
                        //创建时间
                        entModule.setCreateTime(new Date());
                        //备注
                        entModule.setRemark(remark);
                        //状态
                        entModule.setStatus(status);
                        //系统模块名称
                        entModule.setSysName(sysName);
                        //系统模块编号, 用来和系统模块关联
                        entModule.setSysModuleCode(sysCode);
                        //模块序号
                        if (i < sysCodeList.size()) {
                            entModule.setSequence(i + 1);
                            i ++;
                        }
                        entModuleList.add(entModule);

                        if (StringUtil.isNotEmpty(String.valueOf(sysModuleDetailMap.get("id")))) {
                            for (Map map : sysModuleDetailList) {
                                if (null != map && map.size() > 0) {
                                    //企业菜单对象
                                    EntMenu entMenu = new EntMenu();
                                    //企业模块编号
                                    entMenu.setSysCode(newCode);
                                    //id
                                    entMenu.setId(CreateIDUtil.getId());
                                    //状态
                                    entMenu.setStatus(Short.parseShort(String.valueOf(map.get("status"))));
                                    //备注
                                    entMenu.setRemark(String.valueOf(map.get("remark")));
                                    //是否支持权限分配(0,否,1是)
                                    entMenu.setAuthStatus(Short.parseShort(String.valueOf(map.get("auth_status"))));
                                    //创建时间
                                    entMenu.setCreateTime(new Date());
                                    //是否支持脱敏(0,否,1是)
                                    entMenu.setDesensitizeStatus(Short.parseShort(String.valueOf(map.get("desensitize_status"))));
                                    //图标
                                    entMenu.setIcon(String.valueOf(map.get("icon")));
                                    //菜单名称
                                    entMenu.setMenuName(String.valueOf(map.get("menu_name")));
                                    //菜单类型1:菜单,2:按钮3,组件
                                    entMenu.setMenuType(Byte.parseByte(String.valueOf(map.get("menu_type"))));
                                    //父级id
                                    entMenu.setParentId(String.valueOf(map.get("parent_id")));
                                    //父级菜单名称
                                    entMenu.setParentName(String.valueOf(map.get("parent_name")));
                                    //序号
                                    entMenu.setSequence(Integer.parseInt(String.valueOf(map.get("sequence"))));
                                    //url
                                    entMenu.setUrl(String.valueOf(map.get("url")));
                                    //companyCode
                                    entMenu.setCompanyCode(companyCode);
                                    //系统菜单编号
                                    entMenu.setSysMenuCode(String.valueOf(map.get("id")));
                                    entMenuList.add(entMenu);
                                }
                            }
                        }
                    } else {
                        logger.info("系统模块编号为" + sysCode + "对应的菜单信息为空!");
                    }
                }
                //批量插入ent_module
                int num = entModuleMapper.addEntModuleList(entModuleList);
                //批量插入ent_menu
                if (num > 0) {
                    entMenuNum = entMenuMapper.addEntMenuList(entMenuList);
                } else {
                    logger.error("企业模块信息插入失败!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常!");
        }
        return entMenuNum;
    }
}
