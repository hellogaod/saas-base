package com.base.saas.manage.service.impl.enterprise;


import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntDictItemConfig;
import com.base.saas.manage.service.enterprise.EntDictItemConfigService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntDictItemConfigMapper;

import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Title :
 * Description : @数据字典明细@
 */

@Service
public class EntDictItemConfigServiceImpl implements EntDictItemConfigService {

    @Resource
    private EntDictItemConfigMapper dictItemConfigMapper;

    @Override
    public List<EntDictItemConfig> getDictItemConfigList(int status,
                                                         String companyCode,
                                                         String itemCode,
                                                         String detailCode,
                                                         String detailName) throws Exception {
        return dictItemConfigMapper.findList(status, companyCode, itemCode, detailCode, detailName);
    }

    @Override
    public ReturnMap updateDictItemConfigStatus(EntDictItemConfig entDictItemConfig) throws Exception {
        ReturnMap map = new ReturnMap();

        UserInfo userInfo = UserContextUtil.getUserInfo();
        Date now = new Date();

        entDictItemConfig.setUpdateUser(userInfo.getAccount());
        entDictItemConfig.setUpdateTime(now);

        int row = dictItemConfigMapper.updateByPrimaryKeySelective(entDictItemConfig);
        if (row >= 1) {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        } else {

            map.setMsg("message.system.operation.fail");
        }
        return map;
    }

    @Override
    public EntDictItemConfig getDictItemConfigById(String id) throws Exception {
        return dictItemConfigMapper.findDictConfigById(id);
    }

    @Override
    public ReturnMap updateDictItemConfig(EntDictItemConfig entDictItemConfig) throws Exception {
        ReturnMap respMap = new ReturnMap();
        if (StringUtil.isEmpty(entDictItemConfig.getId()) || StringUtil.isEmpty(entDictItemConfig.getItemCode())) {
            respMap.setMsg("message.system.request.param.exception");
            return respMap;
        }

        //校验重复字典编码 查询同一字典大类下是否存在相同编码
        List<EntDictItemConfig> entDictItemConfigList = dictItemConfigMapper.findList(-1, entDictItemConfig.getCompanyCode(), entDictItemConfig.getItemCode(), entDictItemConfig.getDetailCode(), null);
        boolean flag = false;
        for (EntDictItemConfig detail : entDictItemConfigList) {//遍历查询结果是否存在相同编码
            if (!detail.getId().equals(entDictItemConfig.getId())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            respMap.setMsg("message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称 查询同一字典大类下是否存在相同编码
        List<EntDictItemConfig> entDictItemConfigs = dictItemConfigMapper.findList(-1, entDictItemConfig.getCompanyCode(), entDictItemConfig.getItemCode(), null, entDictItemConfig.getDetailName());
        for (EntDictItemConfig detail : entDictItemConfigs) {//遍历查询结果是否存在相同编码
            if (!detail.getId().equals(entDictItemConfig.getId())) {
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
        entDictItemConfig.setUpdateTime(now);
        //修改人
        entDictItemConfig.setUpdateUser(UserContextUtil.getUserInfo().getRealName());

        int row = dictItemConfigMapper.updateByPrimaryKeySelective(entDictItemConfig);
        if (row < 1) {
            respMap.setMsg("message.system.update.fail");
        } else {
            respMap.setCode(1);
            respMap.setMsg("message.system.update.success");
        }
        return respMap;
    }

    @Override
    public ReturnMap insertDictItemConfig(EntDictItemConfig entDictItemConfig) throws Exception {
        ReturnMap respMap = new ReturnMap();
        if (StringUtil.isEmpty(entDictItemConfig.getItemCode())) {
            respMap.setMsg("message.system.request.param.exception");
            return respMap;
        }
        //校验重复字典编码
        List<EntDictItemConfig> entDictItemConfigList = dictItemConfigMapper.findList(-1, entDictItemConfig.getCompanyCode(), entDictItemConfig.getItemCode(), entDictItemConfig.getDetailCode(), null);
        if (entDictItemConfigList != null && entDictItemConfigList.size() > 0) {
            respMap.setMsg("message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称
        List<EntDictItemConfig> entDictItemConfigs = dictItemConfigMapper.findList(-1, entDictItemConfig.getCompanyCode(), entDictItemConfig.getItemCode(), null, entDictItemConfig.getDetailName());

        if (entDictItemConfigs != null && entDictItemConfigs.size() > 0) {
            respMap.setMsg("message.dict.name.existed");
            return respMap;
        }
        //校验通过
        Date now = new Date();
        entDictItemConfig.setCreateTime(now);
        entDictItemConfig.setId(CreateIDUtil.getId());//创建人
        entDictItemConfig.setCreateUser(UserContextUtil.getUserInfo().getRealName());
        int row = dictItemConfigMapper.insertSelective(entDictItemConfig);
        if (row < 1) {
            respMap.setMsg("message.system.save.fail");
        } else {
            respMap.setCode(1);
            respMap.setMsg("message.system.save.success");
        }
        return respMap;
    }


    @Override
    public List<Map> getDictByItemCode(String itemCode, String companyCode) throws Exception {
        List<Map> dictDetailList = RedisUtil.get(RedisKeyConstants.DICT_ITEM_CODE + companyCode + "_" + itemCode);
        if (dictDetailList == null || dictDetailList.size() < 1) {
            dictDetailList = new ArrayList<>();
            List<EntDictItemConfig> entDictItemConfigs = dictItemConfigMapper.findList(-1, companyCode, itemCode,null,null);
            for (EntDictItemConfig detail : entDictItemConfigs) {
                if (detail.getStatus().shortValue() != 1) {
                    continue;
                }
                Map dMap = new HashMap();
                dMap.put("dictCode", detail.getDetailCode());
                dMap.put("dictName", detail.getDetailName());
                dMap.put("itemCode", detail.getItemCode());
                dictDetailList.add(dMap);
            }
        }
        return dictDetailList;
    }

}