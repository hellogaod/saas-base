package com.base.saas.manage.service.impl.enterprise;


<<<<<<< HEAD
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntRole;
import com.base.saas.manage.service.enterprise.EntRoleService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntRoleMapper;
=======
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntRoleMapper;
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntRole;
import com.base.saas.manage.service.enterprise.EntRoleService;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import com.base.saas.util.CreateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title :
 * Description :角色管理
 */
@Service
public class EntRoleServiceImpl implements EntRoleService {

    @Autowired
    private EntRoleMapper roleMapper;

    @Override
    public List<EntRole> getRoleList(int status, String roleName, String companyCode) throws Exception {
        List<EntRole> list = roleMapper.getRoleList(status, roleName, companyCode);
        return list;
    }

    @Override
    public ReturnMap addRole(EntRole role) throws Exception {
        ReturnMap map = new ReturnMap();

        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        role.setCreateUser(userInfo.getAccount());
        role.setUpdateUser(userInfo.getAccount());
        role.setCompanyCode(userInfo.getCompanyCode());
        String roleName = role.getRoleName();
        //查询账户是否已存在
        List<EntRole> model = roleMapper.getRoleList(-1, roleName, role.getCompanyCode());
        if (model != null && model.size() > 0) {
            map.setMsg("message.rolename.existed");
            return map;
        }

        String roleId = CreateIDUtil.getId();
        role.setRoleId(roleId);

        Date date = new Date();
        role.setCreateTime(date);
        role.setUpdateTime(date);
        roleMapper.insertSelective(role);

        map.setCode(1);
        map.setMsg("message.system.save.success");
        return map;
    }

    @Override
    public ReturnMap updateState(EntRole role) throws Exception {
        ReturnMap map = new ReturnMap();

        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        role.setUpdateUser(userInfo.getAccount());
        role.setUpdateTime(new Date());
        int n = roleMapper.updateByPrimaryKeySelective(role);

        if (n > 0) {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        } else {
            map.setMsg("message.system.operation.fail");
        }
        return map;
    }

    /**
     * 查询单个角色
     */
    @Override
    public EntRole getRoleById(String roleId) throws Exception {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public ReturnMap updateRole(EntRole role) throws Exception {
        Date date = new Date();
        role.setUpdateTime(date);
        List<EntRole> list = roleMapper.getRoleList(-1, role.getRoleName(), role.getCompanyCode());

        ReturnMap map = new ReturnMap();

        boolean flag = false;
        if (list != null) {
            for (EntRole item : list) {
                if (!item.getRoleId().equals(role.getRoleId())) {
                    flag = true;
                }
            }
            if (flag) {
                map.setMsg("message.rolename.existed");
                return map;
            }
        }

        int n = roleMapper.updateByPrimaryKeySelective(role);
        if (n > 0) {
            map.setCode(1);
            map.setMsg("message.system.update.success");
        } else {
            map.setMsg("message.system.update.fail");
        }
        return map;
    }

}
