package com.base.saas.system.mapper;


import com.base.saas.system.model.EntModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年06月12日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:zhangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Mapper
public interface EntModuleMapper {
    int deleteByPrimaryKey(String sysCode);

    int insert(EntModule record);

    int insertSelective(EntModule record);

    EntModule selectByPrimaryKey(String sysCode);

    int updateByPrimaryKeySelective(EntModule record);

    int updateByPrimaryKey(EntModule record);

    int addEntModuleList(List<EntModule> list);

    int updateEntModuleStatus(String companyCode);

    List<Map> getCompanyModule(@Param("companyCode") String companyCode);

    List<String> getAllBtnIds(@Param("companyCode") String companyCode, @Param("sysCode") String sysCode);
}
