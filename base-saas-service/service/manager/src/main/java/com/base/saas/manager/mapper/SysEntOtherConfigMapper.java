package com.base.saas.manager.mapper;


import com.base.saas.manager.model.SysEntOtherConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysEntOtherConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysEntOtherConfig record);

    int insertSelective(SysEntOtherConfig record);

    SysEntOtherConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysEntOtherConfig record);

    int updateByPrimaryKey(SysEntOtherConfig record);

    //根据条件查询
    List<SysEntOtherConfig> findList(Map map);
    //根据企业编码查询
    List<SysEntOtherConfig> findListByCompanyCode(String companyCode);
    //根据企业编码删除
    int deleteByCompanyCode(String companyCode);
    //批量保存
    int batchSave(List<SysEntOtherConfig> list);
}