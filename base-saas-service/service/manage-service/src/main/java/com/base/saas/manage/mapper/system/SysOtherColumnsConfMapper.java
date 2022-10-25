package com.base.saas.manage.mapper.system;

import com.base.saas.manage.model.system.SysOtherColumnsConf;
import com.base.saas.manage.model.system.SysOtherConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOtherColumnsConfMapper {

    //查询数据列表
    List<SysOtherColumnsConf> findList(@Param("otherId") String otherId, @Param("paraCode") String paraCode, @Param("paraName") String paraName);

    //插入一条item
    int insertSelective(SysOtherColumnsConf record);

    //批量保存
    int batchSave(List<SysOtherColumnsConf> list);

    //批量删除不在参数列表中的数据
    int batchDelNotInParamList(SysOtherConfig s);

    //批量修改
    int batchUpdate(List<SysOtherColumnsConf> list);

    //查询已经存在的数据
    List<SysOtherColumnsConf> findExitsWithParaCode(List<SysOtherColumnsConf> list);
}