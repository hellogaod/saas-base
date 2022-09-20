package com.base.saas.system.mapper;


import com.base.saas.system.model.DictItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 字典管理
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
public interface DictItemMapper {
    //根据id删除
    int deleteByPrimaryKey(String id);

    //插入数据
    int insert(DictItem record);

    //插入数据
    int insertSelective(DictItem record);

    //根据主键id查询
    DictItem selectByPrimaryKey(String id);

    //更新数据
    int updateByPrimaryKeySelective(DictItem record);

    //更新数据
    int updateByPrimaryKey(DictItem record);

    //查询数据列表
    List<DictItem> findList(Map map);

    //修改字典状态
    int updateStatus(Map map);
}