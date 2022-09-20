package com.base.saas.system.service.impl;/**
 * Created by win7 on 2018/5/21.
 */

import com.base.saas.system.mapper.DictItemMapper;
import com.base.saas.system.model.DictItem;
import com.base.saas.system.service.DictItemService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @数据字典大类@
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
public class DictItemServiceImpl implements DictItemService {

    @Resource
    private DictItemMapper dictItemMapper;

    @Override
    public List<DictItem> getDictItemList(Map map)throws Exception {
        return dictItemMapper.findList(map);
    }

    @Override
    public Map updateDictItemStatus(DictItem dictItem) throws Exception{
        Date now = new Date();
        Map map = new HashMap();
        dictItem.setUpdateTime(now);
        int row = dictItemMapper.updateByPrimaryKeySelective(dictItem);
        if(row<1){
            map.put("flag",false);
            map.put("msg","message.system.update.fail");
        }else{
            map.put("flag",true);
            map.put("msg","message.system.operation.success");
        }
        return map;
    }

    @Override
    public DictItem getDictItemById(String id) throws Exception{
        return dictItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map updateDictItem(DictItem dictItem)  throws Exception{
        Map respMap = new HashMap();
        //校验重复字典编码
        Map map = new HashMap();
        map.put("companyCode",dictItem.getCompanyCode());
        map.put("itemCode",dictItem.getItemCode());
        //查询是否存在相同的字典编码
        List<DictItem> dictItemList = dictItemMapper.findList(map);
        boolean flag=false;
        for (DictItem item:dictItemList) {//是否存在相同code
            if(!item.getId().equals(dictItem.getId())){
                flag=true;
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
        map.put("companyCode",dictItem.getCompanyCode());
        map.put("realName",dictItem.getItemName());
        List<DictItem> dictItems = dictItemMapper.findList(map);
        for (DictItem item:dictItems) {//是否存在相同code
            if(!item.getId().equals(dictItem.getId())){
                flag=true;
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
        dictItem.setUpdateTime(now);
        int row=dictItemMapper.updateByPrimaryKeySelective(dictItem);
        if(row<1){
            respMap.put("flag",false);
            respMap.put("msg","message.system.update.fail");
        }else{
            respMap.put("flag",true);
            respMap.put("msg","message.system.update.success");
        }
        return respMap;
    }

    @Override
    public Map insertDictItem(DictItem dictItem) throws Exception{
        Map respMap =new HashMap();
        //校验重复字典编码
        Map map = new HashMap();
        map.put("companyCode",dictItem.getCompanyCode());
        map.put("itemCode",dictItem.getItemCode());
        List<DictItem> dictItemList = dictItemMapper.findList(map);
        if(dictItemList!=null&&dictItemList.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.dict.code.existed");
            return respMap;
        }
        //校验重复字典名称
        map = new HashMap();
        map.put("companyCode",dictItem.getCompanyCode());
        map.put("realName",dictItem.getItemName());
        List<DictItem> dictItemLists=dictItemMapper.findList(map);
        if(dictItemLists!=null&&dictItemLists.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.dict.name.existed");
            return respMap;
        }
        //校验通过保存
        Date now = new Date();
        dictItem.setCreateTime(now);
        dictItem.setId(CreateIDUtil.getId());
        int row =dictItemMapper.insertSelective(dictItem);
        if(row<1){
            respMap.put("flag",false);
            respMap.put("msg","message.system.save.fail");

        }else{
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
        }
        return respMap;
    }




}