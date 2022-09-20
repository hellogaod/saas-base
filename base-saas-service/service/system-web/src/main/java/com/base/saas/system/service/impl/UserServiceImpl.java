package com.base.saas.system.service.impl;

import com.base.saas.system.mapper.UserMapper;
import com.base.saas.system.model.User;
import com.base.saas.system.service.UserService;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.MD5Util;
import com.base.saas.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 用户管理
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:28
     * @Params: user
     *              用户实体
     * @Description: 添加用户
     * @return: map
     */
    @Override
    public Map addUser(User user) throws Exception{
        Map map = new HashMap();
        String account = user.getAccount();
        //查询账户是否已存在
        User model = userMapper.getUserByAccount(account, user.getCompanyCode());
        if(model!=null){
            map.put("flag",false);
            map.put("msg","message.user.account.existed");
            return map;
        }
        String userId = CreateIDUtil.getId();
        user.setUserId(userId);
        //密码加密
        user.setPassword(MD5Util.GetMD5Code(user.getPassword()));
        //company_code企业编号,org_id组织编号,employee_id员工编号
        user.setErrorCount(0);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.insertSelective(user);
        map.put("flag",true);
        map.put("msg","message.system.save.success");
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: user
     *              用户实体
     * @Description: 修改用户
     * @return: Map
     */
    @Override
    public Map updateUser(User user) throws Exception{
        Map map = new HashMap();
        if(StringUtil.isEmpty(user.getUserId())){
            map.put("flag",false);
            map.put("msg","message.system.request.param.exception");
            return map;
        }
        Date date = new Date();
        user.setUpdateTime(date);
        List list = userMapper.getUserByUpdate(user);
        if(!ObjectUtils.isEmpty(list)){
            map.put("flag",false);
            map.put("msg","message.user.account.existed");
            return map;
        }
        int n = userMapper.updateByPrimaryKeySelective(user);
        if(n>0){
            map.put("flag",true);
            map.put("msg","message.system.update.success");
        }else{
            map.put("flag",false);
            map.put("msg","message.system.update.fail");
        }
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: userId
     *              用户id
     * @Description: 查询单个用户
     * @return:
     */
    @Override
    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: userId
     *              用户id
     * @Description: 查询单个用户(包含组织名称)
     * @return:
     */
    @Override
    public Map getUserByUserId(String userId) throws Exception{
        return userMapper.selectById(userId);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:30
     * @Params: user
     *             用户实体
     * @Description: 启用，停用
     * @return:
     */
    @Override
    public Map updateState(User user)throws Exception {
        user.setUpdateTime(new Date());
        Short status = user.getStatus();
        if(status == 1){
            user.setErrorCount(0);
        }
        int n = userMapper.updateByPrimaryKeySelective(user);
        Map map = new HashMap();
        if(n>0){
            map.put("flag",true);
            map.put("msg","message.system.operation.success");
        }else{
            map.put("flag",false);
            map.put("msg","message.system.operation.fail");
        }
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:12
     * @Params: userId
     *             用户id
     * @Params: updateUser
     *             操作人
     * @Description: 重置密码
     * @return: Map
     */
    @Override
    public Map resetPassword(String userId, String updateUser) throws Exception{
        Map map = new HashMap();
        User user = new User();
        user.setUserId(userId);
        user.setUpdateUser(updateUser);
        //初始密码进行加密
        String password = MD5Util.GetMD5Code("123456");
        user.setPassword(password);
        user.setUpdateTime(new Date());
        int n = userMapper.updateByPrimaryKeySelective(user);
        if(n>0){
            map.put("flag",true);
            map.put("msg","message.system.operation.success");
        }else{
            map.put("flag",false);
            map.put("msg","message.system.operation.fail");
        }
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:30
     * @Params: map
     *            查询参数
     * @Description: 获取用户列表
     * @return:
     */
    @Override
    public List<User> getUserList(Map map)throws Exception {
//        String account = String.valueOf(map.get("account"));
//        if(StringUtil.isNotEmpty(account)){
//            account = StringUtil.escapeSQLSpecial(account);
//            map.put("account",account);
//        }
//        String realName = String.valueOf(map.get("realName"));
//        if(StringUtil.isNotEmpty(realName)){
//            realName = StringUtil.escapeSQLSpecial(realName);
//            map.put("realName",realName);
//        }
//        String tel = String.valueOf(map.get("tel"));
//        if(StringUtil.isNotEmpty(tel)){
//            tel = StringUtil.escapeSQLSpecial(tel);
//            map.put("tel",tel);
//        }
        List list = userMapper.getUserList(map);
        return list;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 10:44
     * @Params: userId
     *              用户id
     * @Description: 获取用户角色
     * @return: String
     */
    @Override
    public String getRoleIdByUserId(String userId)throws Exception {
        String roleId = userMapper.getRoleIdByUserId(userId);
        return roleId;
    }
}
