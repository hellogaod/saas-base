package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.model.enterprise.EntDictConfig;
import com.base.saas.manage.model.enterprise.EntDictItemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 字典明细管理
 * Create on : 2018年05月21日
 * Copyright (C)
 *
 * @author department:研发部
 * username:
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
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