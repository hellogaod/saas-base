package com.base.saas.manage.service.enterprise;/**
 * Created by win7 on 2018/5/21.
 */


import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntDictItemConfig;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @数据字典明细@
 */
public interface EntDictItemConfigService {

    //数据列表
    List<EntDictItemConfig> getDictItemConfigList(int status,
                                                  String companyCode,
                                                  String itemCode,
                                                  String detailCode,
                                                  String detailName) throws Exception;

    //修改字典明细状态
    ReturnMap updateDictItemConfigStatus(EntDictItemConfig entDictItemConfig) throws Exception;

    //根据id获取字典明细信息
    EntDictItemConfig getDictItemConfigById(String id) throws Exception;

    //修改字典明细
    ReturnMap updateDictItemConfig(EntDictItemConfig entDictItemConfig) throws Exception;

    //保存字典明细
    ReturnMap insertDictItemConfig(EntDictItemConfig entDictItemConfig) throws Exception;

    //查询数据字典
    List<Map> getDictByItemCode(String itemCode, String companyCode) throws Exception;

}
