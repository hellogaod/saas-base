package com.base.saas.system.service.impl;

import com.base.saas.system.mapper.SysParaMapper;
import com.base.saas.system.model.SysPara;
import com.base.saas.system.service.SysParaService;
import com.base.saas.util.StringUtil;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SysParaServiceImpl implements SysParaService {

    @Autowired
    private SysParaMapper sysParaMapper;

    @Override
    public List<Map> getSysParaByCompanyCode(String companyCode) throws Exception{

        return sysParaMapper.getSysParaByCompanyCode(companyCode);
    }


    @Override
    public Map saveSysPara(Map map) throws Exception{
        Map respMap = new HashMap();
        if(null==map.get("companyCode")|| StringUtil.isEmpty(map.get("companyCode").toString())){
            respMap.put("flag",false);
            respMap.put("msg","message.system.request.param.exception");
            return respMap;
        }
        //删除表中已有的数据
        Map param = new HashMap();
        param.put("companyCode",map.get("companyCode"));
        param.put("otherId",map.get("otherId"));
        int dr=sysParaMapper.deleteByCompanyCodeAndOtherId(param);
        List<SysPara> list = new ArrayList<>();
        List<Map> paraList = (List<Map>) map.get("paramDetail");
        for (Map para:paraList) {
            SysPara sysPara = new SysPara();
            sysPara.setParaName(String.valueOf(para.get("paraName")).replace("/",""));
            sysPara.setParaValue(String.valueOf(para.get("paraValue")));
            sysPara.setCompanyCode(String.valueOf(map.get("companyCode")));
            sysPara.setOtherId(String.valueOf(map.get("otherId")));
            sysPara.setRemark(String.valueOf(para.get("remark")));
            sysPara.setConfId(String.valueOf(para.get("confId")));
            list.add(sysPara);
        }
        int sr = sysParaMapper.batchSave(list);
        if(sr>0){
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
            return respMap;
        }
        respMap.put("flag",false);
        respMap.put("msg","message.system.save.fail");
        return respMap;
    }

    @Override
    public String getParaByCompanyAndKey(String companyCode, String key)throws Exception {
        String paraValue = "";
        Map sysPara = RedisUtil.get(RedisKeyConstants.SYS_PARA + key + "_" + companyCode);
        if (sysPara == null) {
            Map param = new HashMap();
            param.put("paraName",key);
            param.put("companyCode",companyCode);
            List<SysPara> sysParaList = sysParaMapper.findList(param);
            paraValue = sysParaList.get(0).getParaValue();
        }else{
            paraValue = String.valueOf(sysPara.get("paraValue"));
        }
        return paraValue;
    }
}