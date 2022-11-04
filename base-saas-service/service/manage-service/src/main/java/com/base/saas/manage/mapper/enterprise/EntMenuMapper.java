package com.base.saas.manage.mapper.enterprise;

import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取所有有效的菜单url
     *
     * @return
     */
    List<Map<String, String>> getAllMenuUrl();

    //修改企业菜单信息
    int updateEntMenuByEntMenu(EntMenu menu);

    List<Map> getEntMenuByEntCode(@Param("userId") String userId, @Param("entModuleCode") String entModuleCode, @Param("companyCode") String companyCode);

    //获取全部菜单树
    List<Map> getAllMenuTree(@Param("sysCode") String sysCode);

    Map getMenuDetail(Map map);

    List<EntMenu> getSubMenuList(@Param("parentId") String parentId, @Param("sysCode") String sysCode);
}