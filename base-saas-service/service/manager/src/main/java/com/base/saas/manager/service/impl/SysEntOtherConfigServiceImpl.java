package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.SysEntOtherConfigMapper;
import com.base.saas.manager.mapper.SysOtherColumnsConfMapper;
import com.base.saas.manager.mapper.SysOtherConfigMapper;
import com.base.saas.manager.model.SysEntOtherConfig;
import com.base.saas.manager.service.SysEntOtherConfigService;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年07月16日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class SysEntOtherConfigServiceImpl implements SysEntOtherConfigService {

    @Autowired
    private SysEntOtherConfigMapper sysEntOtherConfigMapper;

    @Autowired
    private SysOtherConfigMapper sysOtherConfigMapper;

    @Autowired
    private SysOtherColumnsConfMapper otherColumnsConfMapper;

    @Override
    public List<SysEntOtherConfig> getConfigList(String companyCode) {
        return sysEntOtherConfigMapper.findListByCompanyCode(companyCode);
    }

    @Override
    public Map updateCompanyOtherConf(Map map) throws Exception{
        Map respMap = new HashMap();
        if(null==map.get("companyCode")|| StringUtil.isEmpty(String.valueOf(map.get("companyCode")))){
            respMap.put("flag",false);
            respMap.put("msg","message.system.request.param.exception");
            return respMap;
        }
        //删除当前企业下所有关联数据
        sysEntOtherConfigMapper.deleteByCompanyCode(String.valueOf(map.get("companyCode")));
        //批量保存
        List<Map> otherList = (List<Map>) map.get("otherList");
        List<SysEntOtherConfig> list = new ArrayList<>();
        Date now = new Date();
        if(otherList!=null&&otherList.size()>0){
            for (Map other:otherList) {
                SysEntOtherConfig entOtherConfig = new SysEntOtherConfig();
                entOtherConfig.setId(CreateIDUtil.getId());
                entOtherConfig.setCompanyCode(String.valueOf(map.get("companyCode")));
                entOtherConfig.setOtherId(String.valueOf(other.get("id")));
                entOtherConfig.setOtherName(String.valueOf(other.get("otherName")));
                entOtherConfig.setCreateUser(String.valueOf(map.get("createUser")));
                entOtherConfig.setCreateTime(now);
                list.add(entOtherConfig);
            }
        }
//        //查询系统配置
//        Map param = new HashMap();
//        param.put("type",(short)0);
//        param.put("status",(short)1);
//        List<SysOtherConfig>sysOthers= sysOtherConfigMapper.findList(param);
//        if(sysOthers!=null&&!sysOthers.isEmpty()){
//            for (SysOtherConfig o:sysOthers) {
//                SysEntOtherConfig entOtherConfig = new SysEntOtherConfig();
//                entOtherConfig.setId(CreateIDUtil.getId());
//                entOtherConfig.setCompanyCode(String.valueOf(map.get("companyCode")));
//                entOtherConfig.setCreateUser(String.valueOf(map.get("createUser")));
//                entOtherConfig.setCreateTime(now);
//                entOtherConfig.setOtherId(o.getId());
//                entOtherConfig.setOtherName(o.getOtherName());
//                list.add(entOtherConfig);
//            }
//        }
        if(list!=null&&list.size()>0){
            int sr=sysEntOtherConfigMapper.batchSave(list);
        }
        respMap.put("flag",true);
        respMap.put("msg","message.system.operation.success");
        return respMap;
    }


}