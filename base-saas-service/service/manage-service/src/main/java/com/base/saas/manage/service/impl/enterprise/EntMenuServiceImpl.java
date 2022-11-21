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

    /**
     * 根据企业模块code 获取模块菜单
     *
     * @param entModuleCode 企业模块Code
     * @param companyCode   企业code
     * @return
     */
    @Override
    public Map<String, Object> getEntMenuByEntCode(String userId, String entModuleCode, String companyCode) throws Exception {
        Map<String, Object> returnMap = new HashMap<>(4);
        List<Map<String, Object>> menuList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Map<String, Map<String, String>> dataRoleList = new HashMap<>();
        List moduleList = new ArrayList<>();
        //查询企业所有启用的菜单及按钮
        List<String> allBtnIds = new ArrayList<>();// entModuleMapper.getAllBtnIds(companyCode,entModuleCode);
        List<Map> list = entMenuMapper.getEntMenuByEntCode(userId, entModuleCode, companyCode);

        list.stream().filter(map -> "2".equals(map.get("menu_type").toString())).forEach(map -> {
            String btnId = map.get("sysMenuCode").toString();
            allBtnIds.add(btnId);
        });
        Map<String, List<Map<String, String>>> parentMap = new HashMap<>();
        list.stream().filter(map -> "#".equals(map.get("parent_id"))).forEach(map -> {
            Map<String, Object> menuMap = new HashMap();
            menuMap.put("menuId", map.get("sysMenuCode"));
            menuMap.put("icon", map.get("icon"));
            menuMap.put("name", map.get("menu_name"));
            menuMap.put("url", map.get("url"));
            List<Map<String, String>> subMenu = new ArrayList<>();
            menuMap.put("subMenu", subMenu);
            parentMap.put((String) map.get("sysMenuCode"), subMenu);
            if (!"".equals(map.get("url"))) {
                permissionList.add((String) map.get("url"));
                Map<String, String> dataRole = new HashMap<>(2);
                dataRole.put("org_path", (String) map.get("org_path"));
                dataRole.put("is_desensite", ((Integer) map.get("is_desensite")).toString());
                dataRoleList.put((String) map.get("url"), dataRole);
            }
            menuList.add(menuMap);
        });

        list.stream().filter(map -> !"#".equals(map.get("parent_id"))).forEach(map -> {
            Map<String, String> menuMap = new HashMap(4);
            menuMap.put("menuId", (String) map.get("sysMenuCode"));
            menuMap.put("icon", (String) map.get("icon"));
            menuMap.put("name", (String) map.get("menu_name"));
            menuMap.put("url", (String) map.get("url"));
            if (!"".equals(map.get("url"))) {
                Map<String, String> dataRole = new HashMap<>(2);
                dataRole.put("org_path", (String) map.get("org_path"));
                dataRole.put("is_desensite", ((Integer) map.get("is_desensite")).toString());
                dataRoleList.put((String) map.get("url"), dataRole);
                permissionList.add((String) map.get("url"));
            }
            if (parentMap.containsKey(map.get("parent_id"))) {
                parentMap.get(map.get("parent_id")).add(menuMap);
            }
        });
        //获取企业模块,根据company_code查询ent_module
        moduleList = entModuleMapper.getModuleByCompanyCode(companyCode);
        returnMap.put("menuList", menuList);
        returnMap.put("moduleList", moduleList);
        returnMap.put("permissionList", permissionList);
        returnMap.put("dataRoleList", dataRoleList);
        returnMap.put("allBtnIds", allBtnIds);
        return returnMap;
    }

    @Override
    public List<Map> getAllMenuTree(String sysCode) throws Exception {
        List list = entMenuMapper.getAllMenuTree(sysCode);
        return list;
    }

    @Override
    public Map getMenuDetail(String menuId, String roleId, String userId, String sysCode) throws Exception {
        Map map = new HashMap();
        map.put("menuId", menuId);
        map.put("roleId", roleId);
        map.put("userId", userId);
        map.put("sysCode", sysCode);
        Map retMap = entMenuMapper.getMenuDetail(map);
        return retMap;
    }

    @Override
    public List<EntMenu> getSubMenuList(String parentId, String moduleId) throws Exception {
        return entMenuMapper.getSubMenuList(parentId, moduleId);
    }
}
