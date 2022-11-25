package com.base.saas.manage.mapper.system;

import com.base.saas.manage.domain.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    //根据menuId查询菜单详情
    SysMenu selectByPrimaryKey(String menuId);

    //插入一条item
    int insertSelective(SysMenu record);

    //根据menuId更新item记录
    int updateByPrimaryKeySelective(SysMenu record);

    //获取菜单列表，可根据moduleId 和 status进行筛选
    List<SysMenu> getSysMenuList(@Param("moduleId") String moduleId, @Param("parentId") String parentId, @Param("status") int status);

    //根据moduleId模块id获取全部菜单树
    List<SysMenu> getAllMenuTreeByModuleId(@Param("moduleId") String moduleId, @Param("parentId") String parentId);

    //根据parentId获取当前菜单下的子菜单，并且根据userid判断当前用户对当前菜单是否可脱敏
    List<SysMenu> getChildrenMenuByParentId(@Param("userId") String userId, @Param("parentId") String parentId);

    //当前menu路径是否已经存在于数据库当前模块下的菜单集合中
    int selectCountByMenuUrl(@Param("moduleId") String moduleId,@Param("menuId") String menuId, @Param("url") String url);

    //启用或暂停：本身及其下面的子菜单全部启动或暂停
    int updateSysMenuStatus(@Param("menuId") String menuId, @Param("updateUser") String updateUser, @Param("status") int status);

}