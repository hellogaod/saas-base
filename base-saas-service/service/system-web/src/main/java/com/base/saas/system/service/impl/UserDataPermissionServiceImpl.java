package com.base.saas.system.service.impl;

import com.base.saas.system.mapper.UserDataPermissionMapper;
import com.base.saas.system.model.UserDataPermission;
import com.base.saas.system.service.UserDataPermissionService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年05月25日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class UserDataPermissionServiceImpl implements UserDataPermissionService {
    @Autowired
    private UserDataPermissionMapper userDataPermissionMapper;
    /**
     * @Author: wangtao
     * @Date: 2018/05/25 13:18
     * @Params: userDataPermisson
     *              数据权限实体
     * @Description: 添加数据权限
     * @return: Map
     */
    @Override
    public Map addUserDataPermisson(UserDataPermission userDataPermisson)throws Exception {
        Map map = new HashMap();
        userDataPermisson.setCreateTime(new Date());
        userDataPermisson.setUpdateTime(new Date());
        userDataPermisson.setId(CreateIDUtil.getId());
        int n = userDataPermissionMapper.insertSelective(userDataPermisson);
        if(n>0){
            map.put("flag",true);
            map.put("msg","message.system.save.success");
        }else{
            map.put("flag",false);
            map.put("msg","message.system.save.fail");
        }
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 16:42
     * @Params: userDataPermisson 数据权限实体
     * @Params: type:1-修改数据权限 2-修改脱敏状态
     * @Description: 修改数据权限
     * @return: Map
     */
    @Override
    public Map updateUserDataPermisson(UserDataPermission userDataPermisson, String type)throws Exception {
        Map map = new HashMap();
        userDataPermisson.setUpdateTime(new Date());
        int n = 0;
        if("1".equals(type)){
            n = userDataPermissionMapper.updateUserData(userDataPermisson);
        }else if("2".equals(type)){
            n = userDataPermissionMapper.updateByPrimaryKeySelective(userDataPermisson);
        }
        if(n>0){
            map.put("flag",true);
            map.put("msg","message.system.save.success");
        }else{
            map.put("flag",false);
            map.put("msg","message.system.save.fail");
        }
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 13:26
     * @Params:
     * @Description: 获取数据权限
     * @return:
     */
    @Override
    public UserDataPermission getUserDataPermisson(UserDataPermission userDataPermisson)throws Exception{
        return userDataPermissionMapper.getUserDataPermisson(userDataPermisson);
    }

    @Override
    public String getIdByUserIdAndMenuId(UserDataPermission userDataPermisson)throws Exception{
        return userDataPermissionMapper.getIdByUserIdAndMenuId(userDataPermisson);
    }
}
