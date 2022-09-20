package com.base.saas.manager.mapper;

import com.base.saas.manager.model.SysOtherColumnsConf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysOtherColumnsConfMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysOtherColumnsConf record);

    int insertSelective(SysOtherColumnsConf record);

    SysOtherColumnsConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysOtherColumnsConf record);

    int updateByPrimaryKey(SysOtherColumnsConf record);

    //批量保存
    int batchSave(List<SysOtherColumnsConf> list);

    //查询数据列表
    List<SysOtherColumnsConf> findList(Map map);

    int batchDelNotInParamList(Map map);


    int batchUpdate(List<SysOtherColumnsConf> list);

    //查询已经存在的数据
    List<SysOtherColumnsConf> findExitsWithParaCode(List<Map> list);
}