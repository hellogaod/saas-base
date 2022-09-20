package com.base.saas.manager.mapper;

import com.base.saas.manager.model.EntMenu;
import com.base.saas.manager.model.SysModuleDetail;
import org.apache.ibatis.annotations.Mapper;

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

    //根据系统模块编码清除所有关联菜单信息
    int deleteBySysCode(String sysModuleCode);

    int delNotInModuleDetail(Map map);
    //根据系统菜单 修改
    int updateEntMenuBySysMenu(SysModuleDetail menu);

}