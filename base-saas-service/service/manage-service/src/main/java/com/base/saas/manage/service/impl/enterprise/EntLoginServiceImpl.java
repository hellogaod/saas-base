package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.manage.mapper.enterprise.EntModuleMapper;
import com.base.saas.manage.mapper.enterprise.EntLoginMapper;

import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.enterprise.EntUser;

import com.base.saas.manage.service.enterprise.EntLoginService;
import com.base.saas.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Title :
 * Description : 企业用户登录
 */
@Service
public class EntLoginServiceImpl implements EntLoginService {

    @Autowired
    private EntLoginMapper loginMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    @Value("${loginErrorLimit}")
    private String loginErrorLimit;

    @Override
    public ReturnMap<EntUser> entLogin(String account, String password, String companyCode) throws Exception {
        ReturnMap<EntUser> returnMap = new ReturnMap();
        EntUser user = loginMapper.getEntUser(account, companyCode);
        if (user == null) {
            returnMap.setMsg("message.user.userOrCompany.not.exist");
            return returnMap;
        } else {
            String pwdMd5 = MD5Util.GetMD5Code(password);
            String pwdDB = user.getPassword();
            String roleId = user.getRoleId();
            if (pwdDB.equals(pwdMd5)) {

                if (0 == user.getCompanyStatus()) {//企业已停用
                    returnMap.setMsg("message.user.company.stop.used");
                    return returnMap;
                }
                if (0 == user.getOrgStatus()) {//组织架构已停用
                    returnMap.setMsg("message.user.org.stop.used");
                    return returnMap;
                }
                if (null == roleId) {//当前用户未分配角色
                    returnMap.setMsg("message.user.not.assigned.role");
                    return returnMap;
                }
                if (user.getRoleStatus() == 0) {//当前用户分配角色被停用
                    returnMap.setMsg("message.user.role.stop.used");
                    return returnMap;
                }
                int status = user.getStatus();
                switch (status) {
                    case 0://用户被停用
                        returnMap.setMsg("message.user.stop.use");
                        break;
                    case 1:
                        loginMapper.clearEntUserErrCount(user.getUserId(), user.getCompanyCode());
                        returnMap.setMsg("message.system.operation.success");
                        returnMap.setCode(1);
                        returnMap.setT(user);

                        break;
                    case 2:
                        returnMap.setMsg("message.user.locked");
                        break;
                }
                return returnMap;
            } else {

                Integer errLimit = Integer.parseInt(loginErrorLimit);

                int errCount = user.getErrorCount() + 1;
                if (errCount >= errLimit) {
                    //锁定用户
                    loginMapper.lockComUser(user.getUserId(), user.getCompanyCode(), 2);
                } else {
                    //错误次数+1
                    loginMapper.lockComUser(user.getUserId(), user.getCompanyCode(), -1);
                }
                returnMap.setMsg("message.user.password.error");
                return returnMap;
            }
        }
    }

    @Override
    public List<EntModule> getModuleAndMenuList(String userId, String companyCode) throws Exception {


        //获取企业配置的模块,根据company_code查询ent_module
        List<EntModule> moduleList = entModuleMapper.getModuleByCompanyCode(companyCode);

        if (moduleList != null && moduleList.size() > 0) {
            for (EntModule module : moduleList) {
                if (module != null) {

                    List<EntMenu> list = loginMapper.getUserPermissions(userId, companyCode, module.getModuleId());
                    //菜单呈现树形结构
                    List<EntMenu> menuList = getTree(list);

                    module.setMenuList(menuList);
                }

            }

        }

        return moduleList;
    }

    // 获取组织架构的树形结构
    private List<EntMenu> getTree(List<EntMenu> list) {
        List<EntMenu> tree = list.stream().
                filter(item -> "#".equals(item.getParentId()))
                .peek(
                        item -> {
                            List<EntMenu> childrens = getChildrens(item, list);
                            item.setSubMenus(childrens);
                        }
                )
                .collect(Collectors.toList());

        return tree;
    }


    private List<EntMenu> getChildrens(EntMenu root, List<EntMenu> list) {

        List<EntMenu> lists = list.stream()
                .filter(item ->
                        //筛选出下一层元素节点
                        Objects.equals(item.getParentId(), root.getMenuId())
                )
                .map(item ->
                {
                    //递归set子节点
                    List<EntMenu> childrens = this.getChildrens(item, list);
                    item.setSubMenus(childrens);
                    return item;
                })
                .collect(Collectors.toList());

        return lists;
    }

    @Override
    public void updateEntLoginIpInfo(String userId, String companyCode, String remoteAdrr) {
        loginMapper.updateEntUserIpInfo(userId, companyCode, remoteAdrr);
    }

    @Override
    public ReturnMap updateComUserPwd(String account, String companyCode, String oldPwd, String newPwd) throws Exception {
        ReturnMap respMap = new ReturnMap();
        String md5OldPwd = MD5Util.GetMD5Code(oldPwd);//旧密码加密
        EntUser user = loginMapper.getEntUser(account, companyCode);

        if (!user.getPassword().equals(md5OldPwd)) {//旧密码错误
            respMap.setMsg("message.user.oldPassword.error");
            return respMap;
        }

        String md5NewPwd = MD5Util.GetMD5Code(newPwd);//新密码加密

        int row = loginMapper.updateEntUserPwd(user.getUserId(), user.getCompanyCode(), md5NewPwd);//修改密码
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.update.success");
        } else {
            respMap.setMsg("message.system.update.fail");
        }
        return respMap;
    }

}
