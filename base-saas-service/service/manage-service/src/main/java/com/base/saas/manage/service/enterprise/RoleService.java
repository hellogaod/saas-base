package com.base.saas.manage.service.enterprise;


import com.base.saas.manage.model.Role;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年05月23日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface RoleService {
    List<Role> getRoleList(Map map)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:28
     * @Params: role
     *              角色实体
     * @Description: 添加用户
     * @return: map
     */
    Map addRole(Role role) throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: roleId
     *              角色id
     * @Description: 查询单个角色
     * @return:
     */
    Role getRoleById(String roleId)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: role
     *              角色实体
     * @Description: 修改角色
     * @return: Map
     */
    Map updateRole(Role role)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:30
     * @Params: role
     *             角色实体
     * @Description: 启用，停用
     * @return:
     */
    Map updateState(Role role)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/24 09:30
     * @Params:
     * @Description: 角色分配，获取角色
     * @return:
     */
    List<Map> getRole(String companyCode)throws Exception;
}
