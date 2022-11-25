package com.base.saas.manage.mapper.enterprise;

import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntMenuMapper {

    //批量插入菜单信息
    int addEntMenuList(List<EntMenu> list);

    //根据companyCode查询企业菜单id信息
    List<String> getMenuIdsByCompanyCode(String companyCode);

    //根据模块id查询企业菜单信息
    List<EntMenu> getMenusByModuleId(String moduleId);

    //根据角色id查询当前角色拥有的菜单信息
    List<EntMenu> getMenusByRoleId(String moduleId,String userId);

    //删除企业菜单信息
    int deleteEntMenuByCompanyCode(String companyCode);

    //根据menuId查询菜单详情
    EntMenu selectByPrimaryKey(String menuId);

    int getMenuAndSubMenuCount(@Param("menuId") String menuId);

    List<EntMenu> getSubMenuList(@Param("parentId") String parentId, @Param("moduleId") String moduleId);
}