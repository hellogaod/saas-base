package com.base.saas.manage.mapper;

import com.base.saas.manage.model.enterprise.EntMenu;
import com.base.saas.manage.model.system.SysMenu;
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

    //删除企业菜单信息
    int deleteEntMenuByCompanyCode(String companyCode);


    int deleteByPrimaryKey(String id);

    int insert(EntMenu record);

    int insertSelective(EntMenu record);

    EntMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntMenu record);

    int updateByPrimaryKeyWithBLOBs(EntMenu record);

    int updateByPrimaryKey(EntMenu record);

    /**
     * 获取所有有效的菜单url
     *
     * @return
     */
    List<Map<String, String>> getAllMenuUrl();

    //根据系统模块编码清除所有关联菜单信息
    int deleteBySysCode(String sysModuleCode);

    int delNotInModuleDetail(Map map);

    //修改企业菜单信息
    int updateEntMenuByEntMenu(EntMenu menu);

    List<Map> getEntMenuByEntCode(@Param("userId") String userId, @Param("entModuleCode") String entModuleCode, @Param("companyCode") String companyCode);

    //获取全部菜单树
    List<Map> getAllMenuTree(@Param("sysCode") String sysCode);

    List<Map> getAllMenuTree2(@Param("sysCode") String sysCode);

    Map getMenuDetail(Map map);

    List<EntMenu> getSubMenuList(@Param("parentId") String parentId, @Param("sysCode") String sysCode);
}