package com.base.saas.manage.service.enterprise;/**
 * Created by win7 on 2018/5/21.
 */



import com.base.saas.manage.model.DictDetail;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @数据字典明细@
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface DictDetailService {
    //数据列表
    List<DictDetail> getDictDetailList(Map map)throws Exception;
    //修改字典明细状态
    Map updateDictDetailStatus(DictDetail dictDetail)throws Exception;
    //根据id获取字典明细信息
    Map getDictDetailById(String id)throws Exception;
    //修改字典明细
    Map updateDictDetail(DictDetail dictDetail)throws Exception;
    //保存字典明细
    Map insertDictDetail(DictDetail dictDetail)throws Exception;
    //查询数据字典
    List <Map> getDictByItemCode(String itemCode, String companyCode)throws Exception;

    String getDictNameByDictItemAndDetailCode(String itemCode, String detailCode)throws Exception;

}
