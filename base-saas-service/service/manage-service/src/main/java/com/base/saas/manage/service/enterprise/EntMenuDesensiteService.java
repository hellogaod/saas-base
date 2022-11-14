package com.base.saas.manage.service.enterprise;



import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.enterprise.EntUserMenuDesensite;

import java.util.List;

/**
 * Title :
 * Description : 数据权限管理
 */
public interface EntMenuDesensiteService {

    /**
     * 获取模块列表，以及该模块下被选中的菜单，如果当前模块没有菜单被选中，则不输出该模块
     */

    List<EntModule> getUserDataPermisson(String roleId, String userId) throws Exception;


    /**
     * @Description: 添加或修改数据权限
     * @return: Map
     */
    ReturnMap addOrUpdateUserDataPermisson(EntUserMenuDesensite userDataPermisson) throws Exception;

}
