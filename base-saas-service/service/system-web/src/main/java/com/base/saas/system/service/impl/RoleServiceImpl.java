package com.base.saas.system.service.impl;

import com.base.saas.system.mapper.RoleMapper;
import com.base.saas.system.model.Role;
import com.base.saas.system.service.RoleService;
import com.base.saas.util.CreateIDUtil;
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
 * Description :
 * Create on : 2018年05月23日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList(Map map) throws Exception{
        List<Role> list = roleMapper.getRoleList(map);
        return list;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:28
     * @Params: role
     *              角色实体
     * @Description: 添加用户
     * @return: map
     */
    @Override
    public Map addRole(Role role) throws Exception{
        Map map = new HashMap();
        String roleName = role.getRoleName();
        //查询账户是否已存在
        List<Role> model = roleMapper.getRoleByRoleName(roleName, role.getCompanyCode());
        if(model!=null&&model.size()>0){
            map.put("flag",false);
            map.put("msg","message.rolename.existed");
            return map;
        }
        String roleCode = StringUtil.getPinYinHeadChar(roleName).toUpperCase();
        String roleId = CreateIDUtil.getId();
        role.setRoleId(roleId);
        role.setRoleCode(roleCode);
        //company_code企业编号,org_id组织编号
        Date date = new Date();
        role.setCreateTime(date);
        role.setUpdateTime(date);
        roleMapper.insertSelective(role);
        map.put("flag",true);
        map.put("msg","message.system.save.success");
        return map;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: roleId
     *              角色id
     * @Description: 查询单个角色
     * @return: Role
     */
    @Override
    public Role getRoleById(String roleId)throws Exception {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 13:29
     * @Params: role
     *              角色实体
     * @Description: 修改角色
     * @return: Map
     */
    @Override
    public Map updateRole(Role role) throws Exception{
        Date date = new Date();
        role.setUpdateTime(date);
        List list = roleMapper.getRoleByUpdate(role);
        Map map = new HashMap();
        if(!ObjectUtils.isEmpty(list)){
            map.put("flag",false);
            map.put("msg","message.rolename.existed");
            return map;
        }
        int n = roleMapper.updateByPrimaryKeySelective(role);
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
     * @Date: 2018/05/23 13:30
     * @Params: role
     *             角色实体
     * @Description: 启用，停用
     * @return:
     */
    @Override
    public Map updateState(Role role) throws Exception{
        role.setUpdateTime(new Date());
        int n = roleMapper.updateByPrimaryKeySelective(role);
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
     * @Date: 2018/05/24 09:30
     * @Params: companyCode：公司code
     * @Description: 角色分配，获取角色
     * @return:
     */
    @Override
    public List<Map> getRole(String companyCode)throws Exception {
        List<Map> list = roleMapper.getRole(companyCode);
        return list;
    }
}
