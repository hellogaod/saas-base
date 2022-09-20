package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.SysEntOtherConfigMapper;
import com.base.saas.manager.mapper.SysOtherColumnsConfMapper;
import com.base.saas.manager.mapper.SysOtherConfigMapper;
import com.base.saas.manager.mapper.SysParaMapper;
import com.base.saas.manager.model.SysEntOtherConfig;
import com.base.saas.manager.model.SysOtherColumnsConf;
import com.base.saas.manager.model.SysOtherConfig;
import com.base.saas.manager.model.SysPara;
import com.base.saas.manager.service.SysOtherConfigService;
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
public class SysOtherConfigServiceImpl implements SysOtherConfigService {

    @Autowired
    private SysOtherConfigMapper otherConfigMapper;

    @Autowired
    private SysEntOtherConfigMapper entOtherConfigMapper;

    @Autowired
    private SysParaMapper sysParaMapper;

    @Autowired
    private SysOtherColumnsConfMapper otherColumnsConfMapper;


    @Override
    public List<SysOtherConfig> getOtherConfigList(Map map)throws Exception {
//        map.put("type",1);
        return otherConfigMapper.findList(map);
    }

    @Override
    public List<SysOtherConfig> getOtherConfigByCompanyCode(String companyCode) {
        return otherConfigMapper.getConfListByCompanyCode(companyCode);
    }

    @Override
    public Map update(SysOtherConfig sysOtherConfig) throws Exception{
        Map respMap = new HashMap();
        if(StringUtil.isEmpty(sysOtherConfig.getId())){
            respMap.put("flag",false);
            respMap.put("msg","message.system.request.param.exception");
            return respMap;
        }
        //停用时判断 是否存在关联企业
        if(sysOtherConfig.getStatus()!=null&&sysOtherConfig.getStatus().toString().equals("0")){
            Map param = new HashMap();
            param.put("otherId",sysOtherConfig.getId());
            List<SysEntOtherConfig> list=entOtherConfigMapper.findList(param);
            if(list!=null&&list.size()>0){
                respMap.put("flag",false);
                respMap.put("msg","message.otherConfig.company.isconf");
                return respMap;
            }
        }
        sysOtherConfig.setUpdateTime(new Date());
        int row=otherConfigMapper.updateByPrimaryKeySelective(sysOtherConfig);
        if(row>0){
            //修改sys_para数据
//            Map map = new HashMap();
//            map.put("otherId",sysOtherConfig.getId());
//            //查询sys_para关联的所有数据
//            List<SysPara> paras=sysParaMapper.findList(map);
//            //解析当前配置的参数字段
//            String configColumn = sysOtherConfig.getConfigColumn().replaceAll("\r","").replaceAll("\n","").trim();
//            JSONArray jsonArray=JSONObject.parseArray(configColumn);
//            List<Map> mapListJson =jsonArray.toJavaList(Map.class);
//            List<Object> columns = new ArrayList<>();
//            //比对当前删除的配置字段
//            for (SysPara para:paras) {
//                boolean flag=false;
//                for (Map cm:mapListJson) {
//                    if(para.getParaName().equals(String.valueOf(cm.get("code")))){
//                        flag=true;
//                    }
//                }
//                if(!flag) {
//                    columns.add(para.getParaValue());
//                }
//            }
//            //删除已经不存在的字段
//            if(columns!=null&&columns.size()>0){
//                Map dm= new HashMap();
//                dm.put("otherId",sysOtherConfig.getId());
//                dm.put("list",columns);
//                sysParaMapper.deleteNotInConf(dm);
//            }
            respMap.put("flag",true);
            respMap.put("msg","message.system.update.success");
            return respMap;
        }
        respMap.put("flag",false);
        respMap.put("msg","message.system.update.fail");
        return respMap;
    }

    /**
     * 保存
     * @param map
     * @return
     */
    @Override
    public Map saveOtherConfig(Map map) throws Exception{
        Map respMap = new HashMap();
        //判断otherName是否重复
        Map param = new HashMap();
        param = new HashMap();
        param.put("realName",map.get("otherName"));
        List<SysOtherConfig> sysOtherConfigs = otherConfigMapper.findList(param);
        if(sysOtherConfigs!=null&&sysOtherConfigs.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraName.existed");
            return  respMap;
        }
        //判断基础配置仅为一条
        if(String.valueOf(map.get("type")).equals("0")) {
            //判断基础配置是否已存在
            param = new HashMap();
            param.put("type", 0);
            List<SysOtherConfig> list = otherConfigMapper.findList(param);
            if(list!=null&&list.size()>0){
                respMap.put("flag",false);
                respMap.put("msg","message.otherConfig.baseConfig.isOnlyOne");
                return respMap;
            }
        }
        //判断参数字段是否为空
        List<Map> detailList = (List<Map>) map.get("detailList");
        if(detailList==null||detailList.size()<1){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraName.not.null");
            return respMap;
        }
        //判断当前集合中是否有重复paraCode
        List<Map> details = new ArrayList<>();
        boolean b =false;
        for (Map m : detailList) {
            b = details.stream().anyMatch(u -> String.valueOf(u.get("paraCode")).equals(String.valueOf(m.get("paraCode"))));
            if (b) {
                break;
            }
            details.add(m);
        }
        if(b){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        //判断数据库的paraCode是否重复
        List<SysOtherColumnsConf> confs=otherColumnsConfMapper.findExitsWithParaCode(detailList);
        if(confs!=null&&confs.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        //保存sys_other_config
        Date now = new Date();
        String createUser = String.valueOf(map.get("createUser"));
        String id = CreateIDUtil.getId();
        SysOtherConfig sysOtherConfig = new SysOtherConfig();
        sysOtherConfig.setId(id);
        sysOtherConfig.setCreateTime(now);
        sysOtherConfig.setOtherName(String.valueOf(map.get("otherName")));
        sysOtherConfig.setType(Short.valueOf(String.valueOf(map.get("type"))));
        sysOtherConfig.setStatus((short) 1);
        sysOtherConfig.setCreateUser(createUser);
        int sr = otherConfigMapper.insertSelective(sysOtherConfig);
        if(sr>0){
            //保存sys_other_config成功
            //保存sys_other_columns_conf
            List<SysOtherColumnsConf> confList = new ArrayList<>();
            for(Map columns:detailList){
                SysOtherColumnsConf columnsConf = new SysOtherColumnsConf();
                columnsConf.setId(CreateIDUtil.getId());
                columnsConf.setCreateTime(now);
                columnsConf.setCreateUser(createUser);
                columnsConf.setStatus((short) 1);
                columnsConf.setRemark(String.valueOf(columns.get("remark")));
                columnsConf.setOtherId(id);
                columnsConf.setParaCode(String.valueOf(columns.get("paraCode")));
                columnsConf.setParaName(String.valueOf(columns.get("paraName")));
                columnsConf.setSortting(columns.get("sortting")==null||StringUtil.isEmpty(String.valueOf(columns.get("sortting")))?null: Integer.valueOf(String.valueOf(columns.get("sortting"))));
                confList.add(columnsConf);
            }
            int dr=otherColumnsConfMapper.batchSave(confList);
            if(dr>0){
                respMap.put("flag",true);
                respMap.put("msg","message.system.save.success");
                return respMap;
            }
        }
        respMap.put("flag",false);
        respMap.put("msg","message.system.save.fail");
        return respMap;
    }

    /**
     * 修改
     * @param map
     * @return
     */
    @Override
    public Map updateOtherConfig(Map map)throws Exception {
        Map respMap = new HashMap();
        if(null==map.get("otherId")||StringUtil.isEmpty(String.valueOf(map.get("otherId")))){
            respMap.put("flag",false);
            respMap.put("msg","message.system.request.param.exception");
            return respMap;
        }
        Map sparam= new HashMap();
        //判断基础配置仅为一条
        if(String.valueOf(map.get("type")).equals("0")) {
            //判断基础配置是否已存在
            sparam = new HashMap();
            sparam.put("type", 0);
            List<SysOtherConfig> list = otherConfigMapper.findList(sparam);
            if(list!=null&&list.size()>0){
                boolean flag = false;
                for (SysOtherConfig s:list) {
                    if(!s.getId().equals(String.valueOf(map.get("otherId")))){
                        flag=true;
                        break;
                    }
                }
                if(flag) {
                    respMap.put("flag", false);
                    respMap.put("msg", "message.otherConfig.baseConfig.isOnlyOne");
                    return respMap;
                }
            }
        }
        //判断otherName是否重复
        sparam = new HashMap();
        sparam.put("realName",map.get("otherName"));
        List<SysOtherConfig> sysOtherConfigs = otherConfigMapper.findList(sparam);
        if(sysOtherConfigs!=null&&sysOtherConfigs.size()>0) {
            boolean flag= false;
            for (SysOtherConfig s:sysOtherConfigs) {
                if(!s.getId().equals(String.valueOf(map.get("otherId")))){
                    flag=true;
                    break;
                }
            }
            if(flag) {
                respMap.put("flag", false);
                respMap.put("msg", "message.otherConfig.paraName.existed");
                return respMap;
            }
        }
        //判断参数配置字段是否为空
        List<Map> detailList = (List<Map>) map.get("detailList");
        if(detailList==null||detailList.size()<1){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraName.not.null");
            return respMap;
        }
        //判断当前集合中是否有重复paraCode
        List<Map> details = new ArrayList<>();
        boolean b =false;
        for (Map m : detailList) {
            b = details.stream().anyMatch(u -> String.valueOf(u.get("paraCode")).equals(String.valueOf(m.get("paraCode"))));
            if (b) {
                break;
            }
            details.add(m);
        }
        if(b){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        //判断数据库的paraCode是否重复----若把原配置字段删除新增一条同样的字段，也会报参数编码重复
        List<SysOtherColumnsConf> confs=otherColumnsConfMapper.findExitsWithParaCode(detailList);
        for (Map m:detailList) {
            if(b){
                break;
            }
            for (SysOtherColumnsConf c:confs) {
                if(null==m.get("confId")||StringUtil.isEmpty(m.get("confId").toString())){
                    if(String.valueOf(m.get("paraCode")).equals(c.getParaCode())){
                        b=true;
                        break;
                    }
                }else{
                    if(!String.valueOf(m.get("confId")).equals(c.getId())&&String.valueOf(m.get("paraCode")).equals(c.getParaCode())){
                        b=true;
                        break;
                    }
                }
            }
        }
        if(b){
            respMap.put("flag",false);
            respMap.put("msg","message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        Date now = new Date();
        String otherId = String.valueOf(map.get("otherId"));
        String updateUser =String.valueOf(map.get("updateUser"));
        SysOtherConfig sysOtherConfig = new SysOtherConfig();
        sysOtherConfig.setId(otherId);
        sysOtherConfig.setOtherName(String.valueOf(map.get("otherName")));
        sysOtherConfig.setType(Short.valueOf(String.valueOf(map.get("type"))));
        sysOtherConfig.setUpdateTime(now);
        sysOtherConfig.setUpdateUser(String.valueOf(map.get("updateUser")));
        //保存
        int sr = otherConfigMapper.updateByPrimaryKeySelective(sysOtherConfig);
        if(sr>0){
            //保存成功
            //删除数据库中有但准备修改的集合中没有
            //删除sys_other_column_conf
            int dr=otherColumnsConfMapper.batchDelNotInParamList(map);
            //删除sys_para
            int sdr=sysParaMapper.batchDelNotInParamList(map);
            Map param = new HashMap();
            param.put("otherId",otherId);
            //查询数据库原配置的字段
            List<SysOtherColumnsConf> confList = otherColumnsConfMapper.findList(param);
            //修改字段集
            List<SysOtherColumnsConf> updList = new ArrayList<>();
            //与现在准备修改的字段进行比较
            for (SysOtherColumnsConf c:confList) {
                for (Map detail:detailList) {
                    if(detail.get("confId")!=null&&StringUtil.isNotEmpty(detail.get("confId").toString())&&c.getId().equals(detail.get("confId").toString())){
                        c.setParaCode(String.valueOf(detail.get("paraCode")));
                        c.setParaName(String.valueOf(detail.get("paraName")));
                        c.setUpdateUser(updateUser);
                        c.setUpdateTime(now);
                        c.setRemark(String.valueOf(detail.get("remark")));
                        c.setSortting(detail.get("sortting")==null||StringUtil.isEmpty(String.valueOf(detail.get("sortting")))?null: Integer.valueOf(String.valueOf(detail.get("sortting"))));
                        updList.add(c);
                        break;
                    }
                }
            }
            if(updList!=null&&updList.size()>0) {
                //批量修改
                int ur = otherColumnsConfMapper.batchUpdate(updList);
                //批量修改sys_para
                int usysr=sysParaMapper.batchUpdBySysColumnConf(updList);
            }
            //新增
            List<SysOtherColumnsConf> insList = new ArrayList<>();
            for (Map detail:detailList) {
                if(detail.get("confId")==null||StringUtil.isEmpty(detail.get("confId").toString())){
                    SysOtherColumnsConf conf  = new SysOtherColumnsConf();
                    conf.setId(CreateIDUtil.getId());
                    conf.setOtherId(otherId);
                    conf.setParaName(String.valueOf(detail.get("paraName")));
                    conf.setParaCode(String.valueOf(detail.get("paraCode")));
                    conf.setSortting(detail.get("sortting")==null||StringUtil.isEmpty(String.valueOf(detail.get("sortting")))?null: Integer.valueOf(String.valueOf(detail.get("sortting"))));
                    conf.setRemark(String.valueOf(detail.get("remark")));
                    conf.setCreateUser(updateUser);
                    conf.setCreateTime(now);
                    conf.setStatus((short) 1);
                    insList.add(conf);
                }
            }
            if(insList!=null&&insList.size()>0){
                int ir=otherColumnsConfMapper.batchSave(insList);
                //同时添加 关联企业相关的sys_para表
                //查询otherId关联企业
                List<SysEntOtherConfig> entOtherConfigs=entOtherConfigMapper.findList(param);
                List<SysPara> sysParas = new ArrayList<>();
                for (SysEntOtherConfig entOtherConf:entOtherConfigs) {
                    for (SysOtherColumnsConf conf:insList) {
                        SysPara sysPara = new SysPara();
                        sysPara.setOtherId(entOtherConf.getOtherId());
                        sysPara.setCompanyCode(entOtherConf.getCompanyCode());
                        sysPara.setParaName(conf.getParaCode());
                        sysPara.setParaValue("");
                        sysPara.setRemark(conf.getParaName());
                        sysPara.setConfId(conf.getId());
                        sysParas.add(sysPara);
                    }
                }
                int insr=sysParaMapper.batchSave(sysParas);
            }
            respMap.put("flag",true);
            respMap.put("msg","message.system.update.success");
            return respMap;
        }
        respMap.put("flag",false);
        respMap.put("msg","message.system.update.fail");
        return respMap;
    }

    @Override
    public Map getOtherConfInfo(String id) throws  Exception{
        //查询sys_other_config
        SysOtherConfig sysOtherConfig = otherConfigMapper.selectByPrimaryKey(id);
        //查询sys_other_column_conf
        Map param = new HashMap();
        param.put("otherId",id);
        List<SysOtherColumnsConf> confList = otherColumnsConfMapper.findList(param);
        if(sysOtherConfig!=null) {
            Map map = new HashMap();
            map.put("otherId",sysOtherConfig.getId());
            map.put("otherName",sysOtherConfig.getOtherName());
            map.put("type",sysOtherConfig.getType());
            if(confList!=null&&confList.size()>0){
                List<Map> cList=new ArrayList<>();
                for (SysOtherColumnsConf conf:confList) {
                    Map cmap = new HashMap();
                    cmap.put("confId",conf.getId());
                    cmap.put("otherId",conf.getOtherId());
                    cmap.put("sortting",conf.getSortting());
                    cmap.put("paraCode",conf.getParaCode());
                    cmap.put("paraName",conf.getParaName());
                    cmap.put("remark",conf.getRemark());
                    cmap.put("status",conf.getStatus());
                    cList.add(cmap);
                }
                map.put("detailList",cList);
            }
            return map;
        }
        return null;
    }


}