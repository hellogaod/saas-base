package com.base.saas.manage.mapper;

import com.base.saas.manage.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface UserMapper {
    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:33
     * @Params: userId
     *              用户id
     * @Description: 根据id删除用户
     * @return:
     */
    int deleteByPrimaryKey(String userId);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:33
     * @Params: record
     *              用户实体
     * @Description: 插入数据
     * @return:
     */
    int insert(User record);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:33
     * @Params: record
     *              用户实体
     * @Description: 插入数据
     * @return:
     */
    int insertSelective(User record);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:34
     * @Params: userId
     *              用户id
     * @Description: 根据主键id查询
     * @return:
     */
    User selectByPrimaryKey(String userId);

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 17:06
     * @Params: userId：用户id
     * @Description: 获取用户信息（包含组织名称）
     * @return: Map
     */
    Map selectById(String userId);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:34
     * @Params: account
     *              账户名
     * @Description: 根据账户名查询用户
     * @return:
     */
    User getUserByAccount(@Param("account") String account, @Param("companyCode") String companyCode);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:35
     * @Params: record
     *              用户实体
     * @Description: 更新数据
     * @return:
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:35
     * @Params: record
     *              用户实体
     * @Description: 更新数据
     * @return:
     */
    int updateByPrimaryKey(User record);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:36
     * @Params: user
     *            用户实体
     * @Description: 获取用户列表
     * @return:
     */
    List<User> getUserList(Map map);

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 15:59
     * @Params: userId 用户id
     * @Description: 获取角色id
     * @return: String
     */
    String getRoleIdByUserId(String userId);

    //修改查询账号是否已存在
    List<String> getUserByUpdate(User user);

    List<User> getLockedUserList();
}