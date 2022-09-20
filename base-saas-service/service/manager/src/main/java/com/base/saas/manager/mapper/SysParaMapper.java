package com.base.saas.manager.mapper;

import com.base.saas.manager.model.SysOtherColumnsConf;
import com.base.saas.manager.model.SysPara;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */

@Mapper
public interface SysParaMapper {
    List<Map<String, String>> getSysPara();

    List<Map> getSysParaByCompanyCode(String companyCode);


    int batchSave(List<SysPara> list);

    int deleteByCompanyCodeAndOtherId(Map map);

    List<SysPara> findList(Map map);

    int deleteNotInConf(Map map);

    int batchDelNotInParamList(Map map);


    int batchUpdBySysColumnConf(List<SysOtherColumnsConf> list);


    String getParaValueByParaName(Map map);


    /**
     * 获取配置参数集合
     */
    List<Map> getSysParaByName(Map map);

}
