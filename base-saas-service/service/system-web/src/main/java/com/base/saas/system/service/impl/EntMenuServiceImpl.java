package com.base.saas.system.service.impl;

import com.base.saas.system.mapper.EntMenuMapper;
import com.base.saas.system.mapper.EntModuleMapper;
import com.base.saas.system.model.EntMenu;
import com.base.saas.system.service.EntMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    EntMenuMapper entMenuMapper;
    @Autowired
    EntModuleMapper entModuleMapper;
    /**
     * 根据企业模块code 获取模块菜单
     * @param entModuleCode 企业模块Code
     * @param companyCode 企业code
     * @return
     */
    @Override
    public Map<String, Object> getEntMenuByEntCode(String userId, String entModuleCode, String companyCode) throws Exception{
        Map<String, Object> returnMap = new HashMap<>(4);
        List<Map<String, Object>> menuList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Map<String, Map<String, String>> dataRoleList = new HashMap<>();
        List moduleList = new ArrayList<>();
        //查询企业所有启用的菜单及按钮
        List<String> allBtnIds = new ArrayList<>();// entModuleMapper.getAllBtnIds(companyCode,entModuleCode);
        List<Map> list = entMenuMapper.getEntMenuByEntCode(userId,entModuleCode,companyCode);

        list.stream().filter(map->"2".equals(map.get("menu_type").toString())).forEach(map -> {
            String btnId=map.get("sysMenuCode").toString();
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
        moduleList = entModuleMapper.getCompanyModule(companyCode);
        returnMap.put("menuList", menuList);
        returnMap.put("moduleList", moduleList);
        returnMap.put("permissionList", permissionList);
        returnMap.put("dataRoleList", dataRoleList);
        returnMap.put("allBtnIds",allBtnIds);
        return returnMap;
    }

    @Override
    public List<Map> getAllMenuTree(String sysCode) throws Exception{
        List list = entMenuMapper.getAllMenuTree(sysCode);
        return list;
    }

    @Override
    public Map getMenuDetail(String menuId, String roleId, String userId, String sysCode) throws Exception{
        Map map = new HashMap();
        map.put("menuId",menuId);
        map.put("roleId",roleId);
        map.put("userId",userId);
        map.put("sysCode",sysCode);
        Map retMap = entMenuMapper.getMenuDetail(map);
        return retMap;
    }

    @Override
    public List<EntMenu> getSubMenuList(String parentId, String sysCode) throws Exception{
        return entMenuMapper.getSubMenuList(parentId, sysCode);
    }
}
