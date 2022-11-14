package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.domain.entity.enterprise.EntOtherConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntOtherConfigMapper {

    //根据条件查询
    List<EntOtherConfig> findList(@Param("otherId") String otherId, @Param("companyCode") String companyCode);

    //根据条件查询otherids-企业关联的三方配置
    List<String> findOtherIds( @Param("companyCode") String companyCode);

    //根据企业编码删除
    int deleteByCompanyCode(String companyCode);

    //批量保存
    int batchSave(List<EntOtherConfig> list);

}