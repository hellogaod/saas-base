package com.base.saas.system.service;


import com.base.saas.system.model.User;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 用户管理
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface UserService {
    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:28
     * @Params: user
     *              用户实体
     * @Description: 添加用户
     * @return: map
     */
    Map addUser(User user) throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: user
     *              用户实体
     * @Description: 修改用户
     * @return: Map
     */
    Map updateUser(User user)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: userId
     *              用户id
     * @Description: 查询单个用户
     * @return:
     */
    User getUserById(String userId);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: userId
     *              用户id
     * @Description: 查询单个用户(包含组织名称)
     * @return:
     */
    Map getUserByUserId(String userId)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:30
     * @Params: user
     *             用户实体
     * @Description: 启用，停用
     * @return:
     */
    Map updateState(User user)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:12
     * @Params: userId
     *             用户id
     * @Params: updateUser
     *             操作人
     * @Description: 重置密码
     * @return: int
     */
    Map resetPassword(String userId, String updateUser) throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:30
     * @Params: map
     *            查询参数
     * @Description: 获取用户列表
     * @return:
     */
    List<User> getUserList(Map map)throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 10:44
     * @Params: userId
     *              用户id
     * @Description: 获取用户角色
     * @return: String
     */
    String getRoleIdByUserId(String userId)throws Exception;
}
