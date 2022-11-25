package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.manage.mapper.enterprise.EntModuleMapper;
import com.base.saas.manage.mapper.system.SysMenuMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.system.SysMenu;
import com.base.saas.manage.service.enterprise.EntMenuService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntMenuMapper;
import com.base.saas.util.CreateIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Title :
 * Description : @企业菜单@
 * Create on : 2018年06月08日
 * Copyright (C)
 *
 * @author department:研发部
 * username:chengrui
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class EntMenuServiceImpl implements EntMenuService {

    private static Logger logger = LoggerFactory.getLogger(EntMenuServiceImpl.class);

    @Autowired
    private SysMenuMapper sysModuleDetailMapper;

    @Autowired
    private EntMenuMapper entMenuMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    /**
     * @Description:
     * @Param: * @param sysCodeList系统模块编号  @param companyCode 企业编号
     * @return: int
     * @Date: 2018/6/8
     */
    @Transactional
    @Override
    public ReturnMap addEntMenuAndEntModule(List<String> moduleIds, String companyCode) {

        UserInfo userInfo = UserContextUtil.getUserInfo();

        ReturnMap returnMap = new ReturnMap(0,"system.server.exception");

        try {
            //删除之前本企业的所有的模块
            entModuleMapper.deleteEntModuleByCompanyCode(companyCode);
            //删除之前本企业的所有的菜单
            entMenuMapper.deleteEntMenuByCompanyCode(companyCode);

            if (null != moduleIds && moduleIds.size() > 0) {
                //创建企业系统模块对象集合
                List<EntModule> entModuleList = new ArrayList<>();
                //创建企业菜单对象集合
                List<EntMenu> entMenuList = new ArrayList<>();
                int i = 0;
                for (String moduleId : moduleIds) {
                    //通过sysCode查询平台系统模块对应的菜单信息以及模块信息
                    List<SysMenu> sysMenuList = sysModuleDetailMapper.getSysMenuList(moduleId, null, -1);

                    //遍历系统菜单信息
                    if (null != sysMenuList && sysMenuList.size() > 0) {
                        Date now = new Date();

                        //企业系统模块对象
                        EntModule entModule = new EntModule();
                        //企业系统模块的sys_code
                        String id = CreateIDUtil.getId();
                        //企业模块id
                        entModule.setId(id);
                        //企业编号
                        entModule.setCompanyCode(companyCode);
                        entModule.setCreateUser(userInfo.getAccount());
                        //创建时间
                        entModule.setCreateTime(now);
                        entModule.setUpdateUser(userInfo.getAccount());
                        entModule.setUpdateTime(now);
                        //系统模块id
                        entModule.setModuleId(moduleId);

                        entModuleList.add(entModule);

                        if (sysMenuList != null) {
                            for (SysMenu sysMenu : sysMenuList) {
                                if (null != sysMenu) {
                                    //企业菜单对象
                                    EntMenu entMenu = new EntMenu();
                                    //id
                                    entMenu.setId(CreateIDUtil.getId());
                                    //companyCode
                                    entMenu.setCompanyCode(companyCode);
                                    entMenu.setParentId(sysMenu.getParentId());
                                    entMenu.setCreateUser(userInfo.getAccount());
                                    entMenu.setCreateTime(now);
                                    entMenu.setUpdateUser(userInfo.getAccount());
                                    entMenu.setUpdateTime(now);
                                    entMenu.setModuleId(moduleId);
                                    entMenu.setMenuId(sysMenu.getMenuId());

                                    entMenuList.add(entMenu);
                                }
                            }
                        }
                    } else {
                        logger.info("系统模块编号为" + moduleId + "对应的菜单信息为空!");
                    }
                }
                //批量插入ent_module
                int num = entModuleMapper.addEntModuleList(entModuleList);
                //批量插入ent_menu
                if (num > 0) {
                    if (entMenuMapper.addEntMenuList(entMenuList) > 0) {
                        returnMap.setCode(1);
                        returnMap.setMsg("message.system.operation.success");
                        return returnMap;
                    }
                } else {
                    returnMap.setMsg("message.module.insert.error");
                    logger.error("message.module.insert.error");
                    return returnMap;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("system.server.exception");
            return returnMap;
        }
        return returnMap;
    }

}
