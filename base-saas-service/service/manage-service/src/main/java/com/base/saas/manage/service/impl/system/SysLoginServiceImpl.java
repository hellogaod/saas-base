package com.base.saas.manage.service.impl.system;


import com.base.saas.manage.mapper.system.SysLoginMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.system.SysUser;
import com.base.saas.manage.service.system.SysLoginService;
import com.base.saas.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月24日
 * Copyright (C)
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysLoginMapper sysLoginMapper;

    @Value("${loginErrorLimit}")
    private String loginErrorLimit;

    @Override
    public ReturnMap<SysUser> login(String account, String password) throws Exception {

        ReturnMap returnMap = new ReturnMap<SysUser>(0);

        SysUser user = sysLoginMapper.getUser(account);
        if (user == null) {
            returnMap.setMsg("message.user.not.exist");
        } else {
            String pwdMd5 = password;// MD5Util.GetMD5Code(password);
            String pwdDB = user.getPassword();
            int status = user.getStatus();
            if (pwdDB.equals(pwdMd5)) {
                switch (status) {
                    case 0:
                        returnMap.setMsg("message.user.stop.use");
                        break;
                    case 1:
                        sysLoginMapper.clearErrCount(user.getUserId());
                        returnMap.setCode(1);
                        returnMap.setMsg("message.system.operation.success");
                        break;
                    case 2:
                        returnMap.setMsg("message.user.locked");
                        break;
                }
            } else {
                int errLimit = Integer.parseInt(loginErrorLimit);
                int errCount = user.getErrorCount() + 1;
                if (errCount >= errLimit) {
                    //锁定用户
                    sysLoginMapper.lockUser(user.getUserId(), "2");
                } else {
                    //错误次数+1
                    sysLoginMapper.lockUser(user.getUserId(), null);
                }
                returnMap.setMsg("message.user.password.error");
            }
        }
        return returnMap;
    }

    @Override
    public ReturnMap<List<EntMenu>> getMenuList(String userId) throws Exception {

        ReturnMap returnMap = new ReturnMap<List<EntMenu>>(1);

        List<EntMenu> menus = sysLoginMapper.getPermissions(userId);

        //一级菜单筛选
        List<EntMenu> menuList = new ArrayList<>();
        //筛选一级菜单：一级菜单下的子菜单一个都塞入submenu中，表示全部显示
        menus.stream()
                .filter(item -> "#".equals(item.getParentId()))
                .forEach(item -> {

                    menuList.add(item);
                });

        returnMap.setT(menuList);
        return returnMap;
    }

    @Override
    public ReturnMap updateUserPwd(String account, String oldPwd, String newPwd) throws Exception {

        ReturnMap returnMap = new ReturnMap(0);

        String md5OldPwd = MD5Util.GetMD5Code(oldPwd);//旧密码加密
        SysUser user = sysLoginMapper.getUser(account);
        if (!user.getPassword().toString().equals(md5OldPwd)) {
            returnMap.setMsg("message.user.oldPassword.error");
            return returnMap;
        }
        String md5NewPwd = MD5Util.GetMD5Code(newPwd);//新密码加密

        int row = sysLoginMapper.updatePwd(user.getUserId(), md5NewPwd);//修改密码
        if (row > 0) {
            returnMap.setCode(1);
            returnMap.setMsg("message.system.update.success");
        } else {
            returnMap.setMsg("message.system.update.fail");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> getUserRoleInfo(String userId) throws Exception {
        return sysLoginMapper.getUserRoleInfo(userId);
    }

    @Override
    public void updateLoginInfo(String userId, String remoteAdrr) throws Exception {
        sysLoginMapper.updateLoginInfo(userId, remoteAdrr);
    }
}
