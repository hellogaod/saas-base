package com.base.saas.system.mapper;


import com.base.saas.system.model.EntMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntMenuMapper {
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

    int addEntMenuList(List<EntMenu> list);

    List<String> getEntMenuListByCompanyCode(String companyCode);

    int updateEntMenuStatus(String companyCode);

    List<Map> getEntMenuByEntCode(@Param("userId") String userId, @Param("entModuleCode") String entModuleCode, @Param("companyCode") String companyCode);

    //获取全部菜单树
    List<Map> getAllMenuTree(@Param("sysCode") String sysCode);

    List<Map> getAllMenuTree2(@Param("sysCode") String sysCode);

    Map getMenuDetail(Map map);

    List<EntMenu> getSubMenuList(@Param("parentId") String parentId, @Param("sysCode") String sysCode);
}