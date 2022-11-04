package com.base.saas.manage.service.enterprise;


<<<<<<< HEAD
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.enterprise.EntUserMenuDesensite;

import java.util.List;
=======
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntModule;
import com.base.saas.manage.model.enterprise.EntUserMenuDesensite;

import java.util.List;
import java.util.Map;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

/**
 * Title :
 * Description : 数据权限管理
 */
public interface EntMenuDesensiteService {

    /**
     * 获取模块列表，以及该模块下被选中的菜单，如果当前模块没有菜单被选中，则不输出该模块
     */
<<<<<<< HEAD
    List<EntModule> getUserDataPermisson(String roleId, String userId) throws Exception;
=======
    List<EntModule> getUserDataPermisson(String roleId,String userId) throws Exception;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

    /**
     * @Description: 添加或修改数据权限
     * @return: Map
     */
    ReturnMap addOrUpdateUserDataPermisson(EntUserMenuDesensite userDataPermisson) throws Exception;

}
