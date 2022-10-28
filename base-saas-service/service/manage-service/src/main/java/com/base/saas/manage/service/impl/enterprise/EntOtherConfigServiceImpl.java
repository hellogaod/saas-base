package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntOtherConfigMapper;
import com.base.saas.manage.mapper.system.SysOtherConfigMapper;
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntOtherConfig;
import com.base.saas.manage.model.system.SysOtherConfig;
import com.base.saas.manage.service.enterprise.EntOtherConfigService;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年07月16日
 * Copyright (C)
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class EntOtherConfigServiceImpl implements EntOtherConfigService {

    @Autowired
    private EntOtherConfigMapper sysEntOtherConfigMapper;

    @Autowired
    private SysOtherConfigMapper sysOtherConfigMapper;

    @Override
    public List<SysOtherConfig> getConfigList(String companyCode) {

        List<SysOtherConfig> sysOtherConfigs = sysOtherConfigMapper.findList(-1, -1, null);

        List<String> otherIds = sysEntOtherConfigMapper.findOtherIds(companyCode);

        //查询企业关联的
        sysOtherConfigs.stream().forEach(item -> {
            if (otherIds.contains(item.getOtherId())) {
                item.setChecked(true);
            }
        });

        return sysOtherConfigs;
    }

    @Override
    public ReturnMap updateCompanyOtherConf(Map map) throws Exception {
        UserInfo user = UserContextUtil.getUserInfo();

        ReturnMap respMap = new ReturnMap();
        //companycode必须存在
        if (null == map.get("companyCode") || StringUtil.isEmpty(String.valueOf(map.get("companyCode")))) {
            respMap.setMsg("message.system.request.param.exception");
            return respMap;
        }
        //删除当前企业下所有关联数据
        sysEntOtherConfigMapper.deleteByCompanyCode(String.valueOf(map.get("companyCode")));
        //批量保存
        List<Map> otherList = (List<Map>) map.get("otherList");
        List<EntOtherConfig> list = new ArrayList<>();
        Date now = new Date();
        if (otherList != null && otherList.size() > 0) {
            for (Map other : otherList) {
                EntOtherConfig entOtherConfig = new EntOtherConfig();
                entOtherConfig.setId(CreateIDUtil.getId());
                entOtherConfig.setCompanyCode(String.valueOf(map.get("companyCode")));
                entOtherConfig.setOtherId(String.valueOf(other.get("otherId")));
                entOtherConfig.setCreateUser(user.getAccount());
                entOtherConfig.setCreateTime(now);
                entOtherConfig.setUpdateUser(user.getAccount());
                entOtherConfig.setUpdateTime(now);
                list.add(entOtherConfig);
            }
        }
        if (list != null && list.size() > 0) {
            int sr = sysEntOtherConfigMapper.batchSave(list);
        }
        respMap.setCode(1);
        respMap.setMsg("message.system.operation.success");
        return respMap;
    }


}