package com.base.saas.manage.service.enterprise;

import com.base.saas.manage.domain.model.ReturnMap;

import java.util.List;

/**
 * Title :
 * Description : @企业菜单@
 */
public interface EntMenuService {

     //根据系统模块的moduleId给企业超级管理员添加模块信息和模块下的全部菜单信息
     ReturnMap addEntMenuAndEntModule(List<String> moduleIds, String companyCode);

}