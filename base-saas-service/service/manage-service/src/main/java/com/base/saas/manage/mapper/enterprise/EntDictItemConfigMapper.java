package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.domain.entity.enterprise.EntDictItemConfig;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Title :
 * Description : 字典明细管理
 */
@Mapper
public interface EntDictItemConfigMapper {

    //插入数据
    int insertSelective(EntDictItemConfig record);

    //更新数据
    int updateByPrimaryKeySelective(EntDictItemConfig record);

    //查询数据列表
    List<EntDictItemConfig> findList(@Param("status") int status,
                                     @Param("companyCode") String companyCode,
                                     @Param("itemCode") String itemCode,
                                     @Param("detailCode") String detailCode,
                                     @Param("detailName") String detailName);

    //根据id关联字典明细
    EntDictItemConfig findDictConfigById(String id);

    //根据条件查询字典明细
    EntDictItemConfig getDictDetailByItemCodeAndCode(@Param("companyCode") String companyCode,
                                                     @Param("itemCode") String itemCode,
                                                     @Param("detailCode") String detailCode);
}