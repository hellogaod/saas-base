package com.base.saas.system.mapper;


import com.base.saas.system.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 角色管理
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
public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRoleList(Map map);

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 14:41
     * @Params: roleName
     *              角色名称
     * @Description: 根据角色名称查角色
     * @return: Role
     */
    List<Role> getRoleByRoleName(@Param("roleName") String roleName, @Param("companyCode") String companyCode);

    /**
     * @Author: wangtao
     * @Date: 2018/05/24 09:30
     * @Params: companyCode：公司code
     * @Description: 角色分配，获取角色
     * @return:
     */
    List<Map> getRole(String companyCode);

    List<String> getRoleByUpdate(Role role);

}