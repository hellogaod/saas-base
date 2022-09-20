package com.base.saas.system.service;/**
 * Created by win7 on 2018/5/21.
 */


import com.base.saas.system.model.DictItem;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @数据字典大类@
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface DictItemService {

    //查询字典大类数据列表
    List<DictItem> getDictItemList(Map map)throws Exception;
    //修改字典大类状态
    Map updateDictItemStatus(DictItem dictItem)throws Exception;
    //根据id查询字典大类
    DictItem getDictItemById(String id)throws Exception;
    //修改字典大类
    Map updateDictItem(DictItem dictItem)throws Exception;
    //保存字典大类
    Map insertDictItem(DictItem dictItem)throws Exception;




}
