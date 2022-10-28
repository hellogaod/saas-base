package com.base.saas.manage.service.impl.enterprise;/**
 * Created by win7 on 2018/5/21.
 */

import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntDictConfigMapper;
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntDictConfig;
import com.base.saas.manage.service.enterprise.EntDictConfigService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Title :
 * Description : 数据字典大类
 */
@Service
public class EntDictConfigServiceImpl implements EntDictConfigService {

    @Resource
    private EntDictConfigMapper dictItemMapper;

    @Override
    public List<EntDictConfig> getDictConfigList(int status,
                                                 String companyCode,
                                                 String itemCode,
                                                 String itemName) throws Exception {
        return dictItemMapper.findList(status, companyCode, itemCode, itemName);
    }

    @Override
    public ReturnMap updateDictConfigStatus(EntDictConfig entDictConfig) throws Exception {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        Date now = new Date();

        ReturnMap map = new ReturnMap();

        entDictConfig.setUpdateTime(now);
        entDictConfig.setUpdateUser(userInfo.getAccount());
        int row = dictItemMapper.updateByPrimaryKeySelective(entDictConfig);
        if (row < 1) {
            map.setMsg("message.system.update.fail");
        } else {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        }
        return map;
    }

    @Override
    public EntDictConfig getDictConfigById(String id) throws Exception {
        return dictItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public ReturnMap updateDictConfig(EntDictConfig entDictConfig) throws Exception {
        ReturnMap respMap = new ReturnMap();

        //校验重复字典编码:查询是否存在相同的字典编码
        List<EntDictConfig> entDictConfigList = dictItemMapper.findList(-1, entDictConfig.getCompanyCode(), entDictConfig.getItemCode(), null);
        boolean flag = false;
        for (EntDictConfig item : entDictConfigList) {//是否存在相同code
            if (!item.getId().equals(entDictConfig.getId())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            respMap.setMsg("message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称
        List<EntDictConfig> entDictConfigs = dictItemMapper.findList(-1, entDictConfig.getCompanyCode(), null, entDictConfig.getItemName());
        for (EntDictConfig item : entDictConfigs) {//是否存在相同code
            if (!item.getId().equals(entDictConfig.getId())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            respMap.setMsg("message.dict.name.existed");
            return respMap;
        }
        //校验通过
        Date now = new Date();
        UserInfo userInfo = UserContextUtil.getUserInfo();
        entDictConfig.setUpdateTime(now);
        entDictConfig.setUpdateUser(userInfo.getAccount());

        int row = dictItemMapper.updateByPrimaryKeySelective(entDictConfig);
        if (row < 1) {
            respMap.setMsg("message.system.update.fail");
        } else {
            respMap.setMsg("message.system.update.success");
            respMap.setCode(1);
        }
        return respMap;
    }

    @Override
    public ReturnMap insertDictConfig(EntDictConfig entDictConfig) throws Exception {
        ReturnMap respMap = new ReturnMap();

        //校验重复字典编码
        List<EntDictConfig> entDictConfigList = dictItemMapper.findList(-1, entDictConfig.getCompanyCode(), entDictConfig.getItemCode(), null);
        if (entDictConfigList != null && entDictConfigList.size() > 0) {
            respMap.setMsg("message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称
        List<EntDictConfig> entDictConfigLists = dictItemMapper.findList(-1, entDictConfig.getCompanyCode(), null, entDictConfig.getItemName());
        if (entDictConfigLists != null && entDictConfigLists.size() > 0) {
            respMap.setMsg("message.dict.name.existed");
            return respMap;
        }
        //校验通过保存
        Date now = new Date();

        UserInfo userInfo = UserContextUtil.getUserInfo();
        entDictConfig.setCreateUser(userInfo.getAccount());//创建人
        entDictConfig.setCompanyCode(userInfo.getCompanyCode());
        entDictConfig.setUpdateUser(userInfo.getAccount());
        entDictConfig.setUpdateTime(now);
        entDictConfig.setCreateTime(now);
        entDictConfig.setId(CreateIDUtil.getId());
        int row = dictItemMapper.insertSelective(entDictConfig);
        if (row < 1) {
            respMap.setMsg("message.system.save.fail");

        } else {
            respMap.setCode(1);
            respMap.setMsg("message.system.save.success");
        }
        return respMap;
    }


}