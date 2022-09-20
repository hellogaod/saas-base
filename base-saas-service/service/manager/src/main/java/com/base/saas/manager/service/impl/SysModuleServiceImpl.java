package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.EntModuleMapper;
import com.base.saas.manager.mapper.SysModuleDetailMapper;
import com.base.saas.manager.mapper.SysModuleMapper;
import com.base.saas.manager.model.SysModule;
import com.base.saas.manager.model.SysModuleDetail;
import com.base.saas.manager.service.SysModuleService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {
    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Autowired
    private SysModuleDetailMapper sysModuleDetailMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    @Override
    public List getEffectiveModule() throws Exception{
        List list = sysModuleMapper.getEffectiveModule();
        return list;
    }

    @Override
    public List<SysModule> getSysModuleList(Map map) throws Exception{
        return sysModuleMapper.findList(map);
    }

    @Override
    public Map saveSysModule(SysModule module)throws Exception {
        Map respMap = new HashMap();
        //判断sysName是否存在重复
        Map map = new HashMap();
        map.put("realName",module.getSysName());
        List<SysModule> modules = sysModuleMapper.findList(map);
        if(modules!=null&&modules.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.module.existed");
            return respMap;
        }
        //校验通过保存
        Date now = new Date();
        module.setCreateTime(now);
        String sysCode = CreateIDUtil.getId();
        module.setSysCode(sysCode);
        //添加系统模块的时候,,默认给每个模块加一个系统管理的菜单
        SysModuleDetail sysModuleDetail = new SysModuleDetail();
        sysModuleDetail.setId(CreateIDUtil.getId());
        sysModuleDetail.setMenuName("系统管理");
        sysModuleDetail.setUrl("");
        sysModuleDetail.setSequence(1);
        sysModuleDetail.setIcon("fa fa-cogs");
        sysModuleDetail.setMenuType((byte) 1);
        sysModuleDetail.setParentId("#");
        sysModuleDetail.setCreateUser(module.getCreateUser());
        sysModuleDetail.setCreateTime(now);
        sysModuleDetail.setUpdateUser(module.getCreateUser());
        sysModuleDetail.setUpdateTime(now);
        sysModuleDetail.setStatus((short) 1);
        sysModuleDetail.setSysCode(sysCode);
        sysModuleDetail.setRemark("系统管理");
        sysModuleDetail.setAuthStatus((short) 0);
        sysModuleDetail.setDesensitizeStatus((short) 0);
        sysModuleDetailMapper.insertSelective(sysModuleDetail);
        int row = sysModuleMapper.insertSelective(module);
        if(row>0){
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
        }else{
            respMap.put("flag",false);
            respMap.put("msg","message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public Map updateSysModule(SysModule module)throws Exception {
        Map respMap = new HashMap();
        //判断 sysName是否重复
        Map map = new HashMap();
        map.put("realName",module.getSysName());
        List<SysModule> modules = sysModuleMapper.findList(map);
        boolean flag = false;
        for (SysModule m:modules){
            if(!m.getSysCode().equals(module.getSysCode())){
                flag = true;
                break;
            }
        }
        if(flag){
            respMap.put("flag",false);
            respMap.put("msg","message.module.existed");
            return respMap;
        }
        //校验通过 保存
        Date now = new Date();
        module.setUpdateTime(now);
        int row = sysModuleMapper.updateByPrimaryKeySelective(module);
        if(row>0){
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
        }else{
            respMap.put("flag",false);
            respMap.put("msg","message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public Map updateSysModuleStatus(SysModule module) throws Exception{
        Map respMap = new HashMap();
        if(module.getStatus()==0){
            //停用--判断该系统模块下是否存在关联企业
            List<Map> entModuleList = entModuleMapper.findAllCompanyCodeBySysCode(module.getSysCode());
            if(entModuleList!=null&&entModuleList.size()>0){
                respMap.put("flag",false);
                respMap.put("msg","message.module.hasCompany");
                return respMap;
            }

        }
        Date now = new Date();
        module.setUpdateTime(now);
        int row = sysModuleMapper.updateByPrimaryKeySelective(module);
        if(row>0){
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
        }else{
            respMap.put("flag",false);
            respMap.put("msg","message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public SysModule getModuleInfo(String sysCode)throws Exception {
        return sysModuleMapper.selectByPrimaryKey(sysCode);
    }
}
