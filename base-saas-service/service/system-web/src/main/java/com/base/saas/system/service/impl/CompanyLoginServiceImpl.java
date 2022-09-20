package com.base.saas.system.service.impl;

import com.base.saas.system.mapper.EntModuleMapper;
import com.base.saas.system.mapper.LoginMapper;
import com.base.saas.system.mapper.SysParaMapper;
import com.base.saas.system.model.SysPara;
import com.base.saas.system.service.CompanyLoginService;
import com.base.saas.util.MD5Util;
import com.base.saas.util.StringUtil;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
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
public class CompanyLoginServiceImpl implements CompanyLoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    @Autowired
    private SysParaMapper sysParaMapper;

    @Value("${loginErrorLimit}")
    private String loginErrorLimit;

    @Override
    public Map<String, String> comLogin(String account, String password, String companyCode) throws Exception {
        Map<String, String> returnMap = new HashMap<>(5);
        Map user = loginMapper.getComUser(account, companyCode);
        if (user == null) {
            returnMap.put("code", "0");
            returnMap.put("message", "message.user.userOrCompany.not.exist");
            return returnMap;
        } else {
            String pwdMd5 = MD5Util.GetMD5Code(password);
            String pwdDB = (String) user.get("password");
            String roleId = (String) user.get("role_id");
            if (pwdDB.equals(pwdMd5)) {
                int e_status = ((Integer) user.get("e_status")).intValue();
                if (0 == e_status) {
                    returnMap.put("code", "0");
                    returnMap.put("message", "message.user.company.stop.used");
                    return returnMap;
                }
                int o_status = ((Integer) user.get("o_status")).intValue();
                if (0 == o_status) {
                    returnMap.put("code", "0");
                    returnMap.put("message", "message.user.org.stop.used");
                    return returnMap;
                }
                if (null == roleId) {
                    returnMap.put("code", "0");
                    returnMap.put("message", "message.user.not.assigned.role");
                    return returnMap;
                }
                int r_status = ((Integer) user.get("r_status")).intValue();
                if (r_status == 0) {
                    returnMap.put("code", "0");
                    returnMap.put("message", "message.user.role.stop.used");
                    return returnMap;
                }
                int status = ((Integer) user.get("status")).intValue();
                switch (status) {
                    case 0:
                        returnMap.put("code", "0");
                        returnMap.put("message", "message.user.stop.use");
                        break;
                    case 1:
                        loginMapper.clearComErrCount((String) user.get("user_id"), String.valueOf(user.get("company_code")));
                        returnMap.put("code", "1");
                        returnMap.put("message", "message.system.operation.success");
                        returnMap.putAll(user);
                        break;
                    case 2:
                        returnMap.put("code", "0");
                        returnMap.put("message", "message.user.locked");
                        break;
                }
                return returnMap;
            } else {
                //从redis缓存取
                Map<String, String> sysPara = RedisUtil.get(RedisKeyConstants.SYS_PARA + "login_err_limit" + "_" + companyCode);
                Integer errLimit = null;
                if (sysPara == null || sysPara.isEmpty() || StringUtil.isEmpty(sysPara.get("paraValue"))) {
                    Map param = new HashMap();
                    param.put("paraName", "login_err_limit");
                    param.put("companyCode", companyCode);
                    //查询数据库
                    List<SysPara> sysParaList = sysParaMapper.findList(param);
                    if (sysParaList != null && sysParaList.size() > 0 && StringUtil.isNotEmpty(sysParaList.get(0).getParaValue())) {
                        errLimit = Integer.valueOf(sysParaList.get(0).getParaValue());
                    } else {
                        //从配置文件取
                        errLimit = Integer.parseInt(loginErrorLimit);
                    }
                } else {
                    errLimit = Integer.parseInt(String.valueOf(sysPara.get("paraValue")));
                }
                int errCount = ((Integer) user.get("error_count")).intValue() + 1;
                if (errCount >= errLimit) {
                    //锁定用户
                    loginMapper.lockComUser((String) user.get("user_id"), String.valueOf(user.get("company_code")), "2");
                } else {
                    //错误次数+1
                    loginMapper.lockComUser((String) user.get("user_id"), String.valueOf(user.get("company_code")), null);
                }
                returnMap.put("code", "0");
                returnMap.put("message", "message.user.password.error");
                return returnMap;
            }
        }
    }

    @Override
    public Map<String, Object> getComRoleList(String userId, String companyCode) throws Exception {
        Map<String, Object> returnMap = new HashMap<>(4);
        List<Map<String, Object>> menuList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Map<String, Map<String, String>> dataRoleList = new HashMap<>();
        List<Map> moduleList = new ArrayList<>();
        //获取企业模块,根据company_code查询ent_module
        moduleList = entModuleMapper.getCompanyModule(companyCode);


        String sysCode = "";
        //默认登录取第一个企业模块的菜单
        if (moduleList != null && moduleList.size() > 0) {
            sysCode = (String) moduleList.get(0).get("sysCode");
        }
        //查询企业所有启用的菜单及按钮
        List<String> allBtnIds = new ArrayList<>();//entModuleMapper.getAllBtnIds(companyCode,sysCode);
        List<Map> list = loginMapper.getComPermissions(userId, companyCode, sysCode);
        list.stream().filter(map -> "2".equals(map.get("menu_type").toString())).forEach(map -> {
            String btnId = map.get("sysMenuCode").toString();
            allBtnIds.add(btnId);
        });

        Map<String, List<Map<String, String>>> parentMap = new HashMap<>();

        list.stream().filter(map -> "#".equals(map.get("parent_id"))).forEach(map -> {
            Map<String, Object> menuMap = new HashMap();
            menuMap.put("menuId", map.get("sysMenuCode"));
            menuMap.put("icon", map.get("icon"));
            menuMap.put("name", map.get("menu_name"));
            menuMap.put("url", map.get("url"));
            List<Map<String, String>> subMenu = new ArrayList<>();
            menuMap.put("subMenu", subMenu);
            parentMap.put((String) map.get("sysMenuCode"), subMenu);
            if (!"".equals(map.get("url"))) {
                permissionList.add((String) map.get("url"));
                Map<String, String> dataRole = new HashMap<>(2);
                dataRole.put("org_path", (String) map.get("org_path"));
                try {
                    dataRole.put("is_desensite", ((Integer) map.get("is_desensite")).toString());
                } catch (Exception e) {
                    dataRole.put("is_desensite", "0");
                }

                dataRoleList.put((String) map.get("url"), dataRole);
            }
            menuList.add(menuMap);
        });

        list.stream().filter(map -> !"#".equals(map.get("parent_id"))).forEach(map -> {
            Map<String, String> menuMap = new HashMap(4);
            menuMap.put("menuId", (String) map.get("sysMenuCode"));
            menuMap.put("icon", (String) map.get("icon"));
            menuMap.put("name", (String) map.get("menu_name"));
            menuMap.put("url", (String) map.get("url"));
            if (!"".equals(map.get("url"))) {
                Map<String, String> dataRole = new HashMap<>(2);
                dataRole.put("org_path", (String) map.get("org_path"));
                try {
                    dataRole.put("is_desensite", ((Integer) map.get("is_desensite")).toString());
                } catch (Exception e) {
                    dataRole.put("is_desensite", "0");
                }
                dataRoleList.put((String) map.get("url"), dataRole);
                permissionList.add((String) map.get("url"));
            }
            if (parentMap.containsKey(map.get("parent_id"))) {
                parentMap.get(map.get("parent_id")).add(menuMap);
            }
        });

        returnMap.put("menuList", menuList);
        returnMap.put("moduleList", moduleList);
        returnMap.put("permissionList", permissionList);
        returnMap.put("dataRoleList", dataRoleList);
        returnMap.put("allBtnIds", allBtnIds);
        return returnMap;
    }

    @Override
    public Map<String, Object> updateComUserPwd(String account, String companyCode, String oldPwd, String newPwd) throws Exception {
        Map<String, Object> respMap = new HashMap<>();
        String md5OldPwd = MD5Util.GetMD5Code(oldPwd);//旧密码加密
        Map user = loginMapper.getComUser(account, companyCode);
        if (!user.get("password").toString().equals(md5OldPwd)) {
            respMap.put("flag", false);
            respMap.put("msg", "message.user.oldPassword.error");
            return respMap;
        }
        String md5NewPwd = MD5Util.GetMD5Code(newPwd);//新密码加密
        Map param = new HashMap();
        param.put("newPwd", md5NewPwd);
        param.put("userId", user.get("user_id"));
        param.put("companyCode", companyCode);
        int row = loginMapper.updateComPwd(param);//修改密码
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
    public void updateComLoginInfo(String userId, String companyCode, String remoteAdrr) {
        loginMapper.updateComLoginInfo(userId, companyCode, remoteAdrr);
    }
}
