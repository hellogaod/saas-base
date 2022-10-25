package com.base.saas.manage.service;



import com.base.saas.manage.model.UserDataPermission;

import java.util.Map;

/**
 * Title :
 * Description : 数据权限管理
 * Create on : 2018年05月25日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface UserDataPermissionService {
    /**
     * @Author: wangtao
     * @Date: 2018/05/25 13:16
     * @Params: userDataPermisson 数据权限实体
     * @Description: 添加数据权限
     * @return: Map
     */
    Map addUserDataPermisson(UserDataPermission userDataPermisson)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 16:42
     * @Params: userDataPermisson 数据权限实体
     * @Params: type:1-修改数据权限 2-修改脱敏状态
     * @Description: 修改数据权限
     * @return: Map
     */
    Map updateUserDataPermisson(UserDataPermission userDataPermisson, String type)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 13:26
     * @Params: userDataPermisson
     * @Description: 获取数据权限
     * @return: UserDataPermission
     */
    UserDataPermission getUserDataPermisson(UserDataPermission userDataPermisson)throws Exception;

    String getIdByUserIdAndMenuId(UserDataPermission userDataPermisson)throws Exception;
}
