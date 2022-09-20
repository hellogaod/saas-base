package com.base.saas.system.mapper;


import com.base.saas.system.model.DictDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 字典明细管理
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Mapper
public interface DictDetailMapper {
    //根据id删除
    int deleteByPrimaryKey(String id);

    //插入数据
    int insert(DictDetail record);

    //插入数据
    int insertSelective(DictDetail record);

    //根据主键id查询
    DictDetail selectByPrimaryKey(String id);

    //更新数据
    int updateByPrimaryKeySelective(DictDetail record);

    //更新数据
    int updateByPrimaryKey(DictDetail record);

    //查询数据列表
    List<DictDetail> findList(Map map);

    //修改状态
    int updateStatus(Map map);

    //根据id关联字典大类查询
    Map findDictDetailById(String id);

    DictDetail getDictDetailByItemCodeAndCode(Map param);
}