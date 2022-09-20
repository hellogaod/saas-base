package com.base.saas.system.service.impl;/**
 * Created by win7 on 2018/5/21.
 */

import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.system.mapper.DictDetailMapper;
import com.base.saas.system.model.DictDetail;
import com.base.saas.system.service.DictDetailService;
import com.base.saas.util.ChkUtil;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

@Service
public class DictDetailServiceImpl implements DictDetailService {

    @Resource
    private DictDetailMapper dictDetailMapper;

    @Override
    public List<DictDetail> getDictDetailList(Map map)throws Exception {
        return dictDetailMapper.findList(map);
    }

    @Override
    public Map updateDictDetailStatus(DictDetail dictDetail)throws Exception {
        Map map = new HashMap();
        Date now = new Date();
        dictDetail.setUpdateTime(now);

        int row =dictDetailMapper.updateByPrimaryKeySelective(dictDetail);
        if(row<1){
            map.put("flag",false);
            map.put("msg","message.system.operation.success");
        }else{
            map.put("flag",true);
            map.put("msg","message.system.operation.fail");
        }
        return map;
    }

    @Override
    public Map getDictDetailById(String id) throws Exception{
        return dictDetailMapper.findDictDetailById(id);
    }

    @Override
    public Map updateDictDetail(DictDetail dictDetail) throws Exception{
        Map respMap = new HashMap();
        if(StringUtil.isEmpty(dictDetail.getId())|| StringUtil.isEmpty(dictDetail.getItemCode())){
            respMap.put("flag",false);
            respMap.put("msg","message.system.request.param.exception");
            return respMap;
        }
        //校验重复字典编码
        Map map = new HashMap();
        map.put("companyCode",dictDetail.getCompanyCode());
        map.put("itemCode",dictDetail.getItemCode());
        map.put("detailCode",dictDetail.getDetailCode());
        //查询同一字典大类下是否存在相同编码
        List<DictDetail> dictDetailList = dictDetailMapper.findList(map);
        boolean flag = false;
        for (DictDetail detail:dictDetailList) {//遍历查询结果是否存在相同编码
            if(!detail.getId().equals(dictDetail.getId())){
                flag = true;
                break;
            }
        }
        if(flag){
            respMap.put("flag",false);
            respMap.put("msg","message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称
        map = new HashMap();
        map.put("companyCode",dictDetail.getCompanyCode());
        map.put("itemCode",dictDetail.getItemCode());
        map.put("realName",dictDetail.getDetailName());
        //查询同一字典大类下是否存在相同编码
        List<DictDetail> dictDetails = dictDetailMapper.findList(map);
        for (DictDetail detail:dictDetails) {//遍历查询结果是否存在相同编码
            if(!detail.getId().equals(dictDetail.getId())){
                flag = true;
                break;
            }
        }
        if(flag){
            respMap.put("flag",false);
            respMap.put("msg","message.dict.name.existed");
            return respMap;
        }
        //校验通过
        Date now = new Date();
        dictDetail.setUpdateTime(now);
        int row = dictDetailMapper.updateByPrimaryKeySelective(dictDetail);
        if(row<1){
            respMap.put("flag",false);
            respMap.put("msg","message.system.update.fail");
        }else {
            respMap.put("flag",true);
            respMap.put("msg","message.system.update.success");
        }
        return respMap;
    }

    @Override
    public Map insertDictDetail(DictDetail dictDetail) throws Exception{
        Map respMap=new HashMap();
        if(StringUtil.isEmpty(dictDetail.getItemCode())){
            respMap.put("flag",false);
            respMap.put("msg","message.system.request.param.exception");
            return respMap;
        }
        //校验重复字典编码
        Map map = new HashMap();
        map.put("companyCode",dictDetail.getCompanyCode());
        map.put("itemCode",dictDetail.getItemCode());
        map.put("detailCode",dictDetail.getDetailCode());
        //查询同一字典大类下是否存在相同编码
        List<DictDetail> dictDetailList = dictDetailMapper.findList(map);
        if(dictDetailList!=null&&dictDetailList.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称
        map = new HashMap();
        map.put("companyCode",dictDetail.getCompanyCode());
        map.put("itemCode",dictDetail.getItemCode());
        map.put("realName",dictDetail.getDetailName());
        List<DictDetail> dictDetails = dictDetailMapper.findList(map);
        if(dictDetails!=null&&dictDetails.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.dict.name.existed");
            return respMap;
        }
        //校验通过
        Date now = new Date();
        dictDetail.setCreateTime(now);
        dictDetail.setId(CreateIDUtil.getId());
        int row = dictDetailMapper.insertSelective(dictDetail);
        if(row<1){
            respMap.put("flag",false);
            respMap.put("msg","message.system.save.fail");
        }else{
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
        }
        return respMap;
    }


    @Override
    public List<Map> getDictByItemCode(String itemCode, String companyCode)throws Exception {
        List<Map> dictDetailList = RedisUtil.get(RedisKeyConstants.DICT_ITEM_CODE+companyCode+"_"+itemCode);
        if(dictDetailList==null||dictDetailList.size()<1){
            dictDetailList = new ArrayList<>();
            Map param = new HashMap();
            param.put("itemCode",itemCode);
            param.put("companyCode",companyCode);
            List<DictDetail>dictDetails = dictDetailMapper.findList(param);
            for (DictDetail detail:dictDetails) {
                if(detail.getStatus().shortValue()!=1){
                    continue;
                }
                Map dMap = new HashMap();
                dMap.put("dictCode",detail.getDetailCode());
                dMap.put("dictName",detail.getDetailName());
                dMap.put("itemCode",detail.getItemCode());
                dictDetailList.add(dMap);
            }
        }
        return dictDetailList;
    }

    @Override
    public String getDictNameByDictItemAndDetailCode(String itemCode, String detailCode) throws Exception {
        String companyCode = UserContextUtil.getUserInfo().getCompanyCode();
        String detailName = "";
        if(StringUtils.isEmpty(detailCode)){
            return "";
        }
        List<Map> dictDetails =   RedisUtil.get(RedisKeyConstants.DICT_ITEM_CODE+companyCode+"_"+itemCode);
        if(ChkUtil.isNotEmpty(dictDetails)){
            for(Map map :dictDetails){
                String dictCode = map.get("dictCode").toString();
                if(dictCode.equals(detailCode)){
                    return  map.get("dictName").toString();
                }
            }
        }else {
            List dictDetailList = new ArrayList<>();
            Map param = new HashMap();
            param.put("itemCode",itemCode);
            param.put("companyCode",companyCode);
            List<DictDetail> list = dictDetailMapper.findList(param);
            for (DictDetail detail:list) {
                if(detail.getStatus().shortValue()!=1){
                    continue;
                }
                Map dMap = new HashMap();
                dMap.put("dictCode",detail.getDetailCode());
                dMap.put("dictName",detail.getDetailName());
                dMap.put("itemCode",detail.getItemCode());
                dictDetailList.add(dMap);
            }
            boolean set = RedisUtil.set(RedisKeyConstants.DICT_ITEM_CODE + companyCode + "_" + itemCode, dictDetailList);
        }
        //redis没有信息
        Map param = new HashMap();
        param.put("itemCode",itemCode);
        param.put("detailCode",detailCode);
        param.put("companyCode",companyCode);
        DictDetail  dictDetail = dictDetailMapper.getDictDetailByItemCodeAndCode(param);
        detailName = (dictDetail==null?"":dictDetail.getDetailName());
        return detailName;
    }
}