package com.base.saas.manage.service.enterprise;

import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @企业菜单@
 */
public interface EntMenuService {

     //根据系统模块的moduleId给企业超级管理员添加模块信息和模块下的全部菜单信息
     ReturnMap addEntMenuAndEntModule(List<String> moduleIds, String companyCode);

     Map<String, Object> getEntMenuByEntCode(String userId, String entModuleCode, String companyCode)throws Exception;

     List<Map> getAllMenuTree(String sysCode)throws Exception;

     Map getMenuDetail(String menuId, String roleId, String userId, String sysCode)throws Exception;

     List<EntMenu> getSubMenuList(String parentId, String sysCode)throws Exception;
}