package com.base.saas.manage.service.impl.enterprise;


import com.base.saas.manage.mapper.enterprise.EntUserMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntUser;
import com.base.saas.manage.service.enterprise.EntUserService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;

import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.MD5Util;
import com.base.saas.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntUserServiceImpl implements EntUserService {
    @Autowired
    private EntUserMapper userMapper;


    @Override
    public List<EntUser> getUserList(String companyCode,
                                     int status,
                                     String account,
                                     String realName,
                                     String tel) throws Exception {
        List list = userMapper.getUserList(companyCode,
                status,
                account,
                realName,
                tel);
        return list;
    }

    @Override
    public ReturnMap addUser(EntUser user) throws Exception {
        ReturnMap map = new ReturnMap();

        //真实姓名不允许叫 超级管理员，因为这个名称在企业创建时，默认创建了真实姓名叫超级管理员的用户
        if (user.getRealName().equals("超级管理员")) {
            map.setMsg("message.realname.use.administrator.error");
            return map;
        }

        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        user.setCreateUser(userInfo.getAccount());
        user.setCompanyCode(userInfo.getCompanyCode());
        user.setUpdateUser(userInfo.getAccount());

        String account = user.getAccount();
        //查询账户是否已存在
        EntUser model = userMapper.selectByPrimaryKey(null, user.getCompanyCode(), account);
        if (model != null) {
            map.setMsg("message.user.account.existed");
            return map;
        }
        String userId = CreateIDUtil.getId();
        user.setUserId(userId);
        //密码加密
        user.setPassword(MD5Util.GetMD5Code(user.getPassword()));

        user.setErrorCount(0);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.insertSelective(user);

        map.setCode(1);
        map.setMsg("message.system.save.success");
        return map;
    }

    @Override
    public ReturnMap updateState(EntUser user) throws Exception {
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        user.setUpdateUser(userInfo.getAccount());
        user.setUpdateTime(new Date());

        Short status = user.getStatus();
        if (status == 1) {
            user.setErrorCount(0);
        }
        int n = userMapper.updateByPrimaryKeySelective(user);
        ReturnMap map = new ReturnMap();
        if (n > 0) {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        } else {
            map.setMsg("message.system.operation.fail");
        }
        return map;
    }


    @Override
    public ReturnMap resetPassword(String userId) throws Exception {

        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String updateUser = userInfo.getAccount();

        ReturnMap map = new ReturnMap();
        EntUser user = new EntUser();
        user.setUserId(userId);
        user.setUpdateUser(updateUser);
        //初始密码进行加密
        String password = MD5Util.GetMD5Code("888888");
        user.setPassword(password);
        user.setUpdateTime(new Date());
        int n = userMapper.updateByPrimaryKeySelective(user);
        if (n > 0) {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        } else {
            map.setMsg("message.system.operation.fail");
        }
        return map;
    }

    @Override
    public EntUser getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId, null, null);
    }

    @Override
    public ReturnMap updateUser(EntUser user) throws Exception {
        ReturnMap map = new ReturnMap();

        if (StringUtil.isEmpty(user.getUserId())) {
            map.setMsg("message.system.request.param.exception");
            return map;
        }

        List list0 = userMapper.checkHasOtherInfo(user);
        String realName = user.getRealName();
        if (realName != null && realName.equals("超级管理员") && list0 != null && list0.size() > 0) {
            map.setMsg("message.realname.use.administrator.error");
            return map;
        }

        List list = userMapper.getUserByUpdate(user);
        if (list != null && list.size() > 0) {
            map.setMsg("message.user.account.existed");
            return map;
        }

        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        user.setUpdateUser(userInfo.getAccount());
        user.setCompanyCode(userInfo.getCompanyCode());
        Date date = new Date();
        user.setUpdateTime(date);
        int n = userMapper.updateByPrimaryKeySelective(user);
        if (n > 0) {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        } else {
            map.setMsg("message.system.operation.fail");
        }
        return map;
    }


}
