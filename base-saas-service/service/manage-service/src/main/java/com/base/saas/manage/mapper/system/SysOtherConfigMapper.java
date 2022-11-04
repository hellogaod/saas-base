package com.base.saas.manage.mapper.system;

import com.base.saas.manage.domain.entity.system.SysOtherConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOtherConfigMapper {
    // 查询所有三方详情列表
    List<SysOtherConfig> findList(@Param("status") int status, @Param("type") int type, @Param("otherName") String otherName);

    //插入一条三方详情
    int insertSelective(SysOtherConfig record);

    //根据otherId获取三方配置详情
    SysOtherConfig selectByPrimaryKey(String otherId);

    //修改三方配置信息
    int updateByPrimaryKeySelective(SysOtherConfig record);

    //当前企业使用了哪些三方配置信息
    List<SysOtherConfig> getConfListByCompanyCode(String companyCode);
}