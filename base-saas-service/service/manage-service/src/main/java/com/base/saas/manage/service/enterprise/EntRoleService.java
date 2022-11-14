package com.base.saas.manage.service.enterprise;



import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntRole;


import java.util.List;

/**
 * Title :
 * Description :角色管理
 */
public interface EntRoleService {

    //获取角色列表

    List<EntRole> getRoleList(int status, String roleName, String companyCode) throws Exception;


    //添加角色
    ReturnMap addRole(EntRole role) throws Exception;

    //启用，停用
    ReturnMap updateState(EntRole role) throws Exception;

    //获取角色详情
    EntRole getRoleById(String roleId) throws Exception;

    //修改角色
    ReturnMap updateRole(EntRole role) throws Exception;

}
