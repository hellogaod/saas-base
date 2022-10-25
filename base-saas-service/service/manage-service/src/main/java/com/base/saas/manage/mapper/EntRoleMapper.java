package com.base.saas.manage.mapper;

import com.base.saas.manage.model.enterprise.EntRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntRoleMapper {
    //插入一条角色信息
    int insertSelective(EntRole record);

    int deleteByPrimaryKey(String roleId);

    int insert(EntRole record);



    EntRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(EntRole record);

    int updateByPrimaryKey(EntRole record);

    List<EntRole> getRoleList();
    List<EntRole> getRoleAllList(EntRole entRole);
    /**
     * @Author: wangtao
     * @Date: 2018/05/23 14:41
     * @Params: roleName
     *              角色名称
     * @Description: 根据角色名称查角色
     * @return: Role
     */
    EntRole getRoleByRoleName(@Param("roleName") String roleName, @Param("companyCode") String companyCode);

    /**
     * @Author: wangtao
     * @Date: 2018/05/24 09:30
     * @Params: companyCode：公司code
     * @Description: 角色分配，获取角色
     * @return:
     */
    List<Map> getAllRole(String companyCode);

    List<String> getRoleByUpdate(EntRole role);

}