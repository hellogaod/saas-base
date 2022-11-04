package com.base.saas.manage.service.enterprise;/**
 * Created by win7 on 2018/5/21.
 */


<<<<<<< HEAD
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntDictConfig;
=======
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntDictConfig;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

import java.util.List;

/**
 * Title :
 * Description : @数据字典大类@
 */
public interface EntDictConfigService {

    //查询字典大类数据列表
    List<EntDictConfig> getDictConfigList(int status,
                                          String companyCode,
                                          String itemCode,
                                          String itemName) throws Exception;

    //修改字典大类状态
    ReturnMap updateDictConfigStatus(EntDictConfig entDictConfig) throws Exception;

    //根据id查询字典大类
    EntDictConfig getDictConfigById(String id) throws Exception;

    //修改字典大类
    ReturnMap updateDictConfig(EntDictConfig entDictConfig) throws Exception;

    //保存字典大类
    ReturnMap insertDictConfig(EntDictConfig entDictConfig) throws Exception;
}
