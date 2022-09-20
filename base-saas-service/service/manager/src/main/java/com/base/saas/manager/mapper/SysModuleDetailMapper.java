package com.base.saas.manager.mapper;

import com.base.saas.manager.model.SysModuleDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysModuleDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysModuleDetail record);

    int insertSelective(SysModuleDetail record);

    SysModuleDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysModuleDetail record);

    int updateByPrimaryKeyWithBLOBs(SysModuleDetail record);

    int updateByPrimaryKey(SysModuleDetail record);

    List<Map> getDetailAndModelListBySysCode(String sysCode);

    List<Map> getDetailMenuListBySysCode(String sysCode);

    int batchSaveDetail(List<SysModuleDetail> list);

    //根据系统菜单修改关联数据
    int updateModuleDetailBySysMenu(SysModuleDetail moduleDetail);

    List<Map> getAllMenuTreeBySysCode(String sysCode);

    List<SysModuleDetail> getAllMenuDetailList(SysModuleDetail moduleDetail);

    List<SysModuleDetail> getOneMenu(String sysCode);

    List<SysModuleDetail> getTwoMenu(String sysCode);

    List<SysModuleDetail> getMenuByParentId(SysModuleDetail moduleDetail);


    List<SysModuleDetail> selectMenuByMenuUrl(SysModuleDetail moduleDetail);

    int updateStatusEnable(SysModuleDetail moduleDetail);

    int updateStatusDisable(SysModuleDetail moduleDetail);
}