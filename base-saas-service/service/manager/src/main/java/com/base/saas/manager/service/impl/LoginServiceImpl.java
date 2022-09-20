package com.base.saas.manager.service.impl;


import com.base.saas.manager.mapper.LoginMapper;
import com.base.saas.manager.service.ILoginService;
import com.base.saas.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Value("${loginErrorLimit}")
    private String loginErrorLimit;

    @Override
    public Map<String, String> login(String account, String password) throws Exception {
        Map<String, String> returnMap = new HashMap<>(5);
        Map user = loginMapper.getUser(account);
        if (user == null) {
            returnMap.put("code", "0");
            returnMap.put("message", "message.user.not.exist");
        } else {
            String pwdMd5 =password;// MD5Util.GetMD5Code(password);
            String pwdDB = (String) user.get("password");
            int status = ((Integer) user.get("status")).intValue();
            if (pwdDB.equals(pwdMd5)) {
                switch (status) {
                    case 0:
                        returnMap.put("code", "0");
                        returnMap.put("message", "message.user.stop.use");
                        break;
                    case 1:
                        loginMapper.clearErrCount((String) user.get("user_id"));
                        returnMap.put("code", "1");
                        returnMap.putAll(user);
                        returnMap.put("message", "message.system.operation.success");
                        break;
                    case 2:
                        returnMap.put("code", "0");
                        returnMap.put("message", "message.user.locked");
                        break;
                }
            } else {
                int errLimit = Integer.parseInt(loginErrorLimit);
                int errCount = ((Integer) user.get("error_count")).intValue() + 1;
                if (errCount >= errLimit) {
                    //锁定用户
                    loginMapper.lockUser((String) user.get("user_id"), "2");
                } else {
                    //错误次数+1
                    loginMapper.lockUser((String) user.get("user_id"), null);
                }
                returnMap.put("code", "0");
                returnMap.put("message", "message.user.password.error");
            }
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> getRoleList(String userId) throws Exception {
        Map<String, Object> returnMap = new HashMap<>(4);
        List<Map<String, Object>> menuList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Map<String, Map<String, String>> dataRoleList = new HashMap<>();

        List<Map> list = loginMapper.getPermissions(userId);
        Map<String, List<Map<String, String>>> parentMap = new HashMap<>();
        list.stream().filter(map -> "#".equals(map.get("parent_id"))).forEach(map -> {
            Map<String, Object> menuMap = new HashMap();
            menuMap.put("menuId", map.get("id"));
            menuMap.put("icon", map.get("icon"));
            menuMap.put("name", map.get("menu_name"));
            menuMap.put("url", map.get("url"));
            List<Map<String, String>> subMenu = new ArrayList<>();
            menuMap.put("subMenu", subMenu);
            parentMap.put((String) map.get("id"), subMenu);
            if (!"".equals(map.get("url"))) {
                permissionList.add((String) map.get("url"));
                Map<String, String> dataRole = new HashMap<>(2);
                dataRole.put("org_path", (String) map.get("org_path"));
                dataRole.put("is_desensite", ((Integer) map.get("is_desensite")).toString());
                dataRoleList.put((String) map.get("url"), dataRole);
            }
            menuList.add(menuMap);
        });

        list.stream().filter(map -> !"#".equals(map.get("parent_id"))).forEach(map -> {
            Map<String, String> menuMap = new HashMap(4);
            menuMap.put("menuId", (String) map.get("id"));
            menuMap.put("icon", (String) map.get("icon"));
            menuMap.put("name", (String) map.get("menu_name"));
            menuMap.put("url", (String) map.get("url"));
            if (!"".equals(map.get("url"))) {
                Map<String, String> dataRole = new HashMap<>(2);
                dataRole.put("org_path", (String) map.get("org_path"));
                dataRole.put("is_desensite", ((Integer) map.get("is_desensite")).toString());
                dataRoleList.put((String) map.get("url"), dataRole);
                permissionList.add((String) map.get("url"));
            }
            if (parentMap.containsKey(map.get("parent_id"))) {
                parentMap.get(map.get("parent_id")).add(menuMap);
            }
        });
        returnMap.put("menuList", menuList);
        returnMap.put("permissionList", permissionList);
        returnMap.put("dataRoleList", dataRoleList);
        return returnMap;
    }

    @Override
    public Map<String, Object> updateUserPwd(String account, String oldPwd, String newPwd) throws Exception {
        Map<String, Object> respMap = new HashMap<>();
        String md5OldPwd = MD5Util.GetMD5Code(oldPwd);//旧密码加密
        Map user = loginMapper.getUser(account);
        if (!user.get("password").toString().equals(md5OldPwd)) {
            respMap.put("flag", false);
            respMap.put("msg", "message.user.oldPassword.error");
            return respMap;
        }
        String md5NewPwd = MD5Util.GetMD5Code(newPwd);//新密码加密
        Map param = new HashMap();
        param.put("newPwd", md5NewPwd);
        param.put("userId", user.get("user_id"));
        int row = loginMapper.updatePwd(param);//修改密码
        if (row > 0) {
            respMap.put("flag", true);
            respMap.put("msg", "message.system.update.success");
        } else {
            respMap.put("flag", false);
            respMap.put("msg", "message.system.update.fail");
        }
        return respMap;
    }

    @Override
    public Map<String, Object> getUserRoleInfo(String userId) throws Exception {
        return loginMapper.getUserRoleInfo(userId);
    }

    @Override
    public void updateLoginInfo(String userId, String remoteAdrr) throws Exception{
        loginMapper.updateLoginInfo(userId, remoteAdrr);
    }
}
