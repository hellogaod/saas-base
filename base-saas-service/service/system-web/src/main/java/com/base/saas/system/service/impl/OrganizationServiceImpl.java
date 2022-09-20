package com.base.saas.system.service.impl;/**
 * Created by win7 on 2018/5/24.
 */

import com.base.saas.system.mapper.CompanyMapper;
import com.base.saas.system.mapper.OrganizationMapper;
import com.base.saas.system.model.Organization;
import com.base.saas.system.service.OrganizationService;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Title :
 * Description : @组织架构管理@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public List<Organization> getOrgList(Map map) throws Exception{
        List<Organization>orgList=organizationMapper.findList(map);//查询所有组织管理
        List<Organization>org=new ArrayList<>();
        for(int i=0;i<orgList.size();i++){
            if("#".equals(orgList.get(i).getParentOrgId())){
                org.add(getSubset(orgList,orgList.get(i)));
            }
        }
        return org;
    }

    private Organization  getSubset(List<Organization>organizations,Organization organization){
        for (int i=0;i<organizations.size();i++){
            if(organization.getOrgId().equals(organizations.get(i).getParentOrgId())){
                //Organization org=getSubset(organizations,organizations.get(i));
                organization.getChildren().add(getSubset(organizations,organizations.get(i)));
            }
        }
        return  organization;
    }

    @Override
    public Map saveOrgnization(Organization organization) throws Exception{
        Map respMap = new HashMap();
        //没有上级组织
        if(StringUtil.isEmpty(organization.getParentOrgId())){
            organization.setParentOrgId("#");
        }
        //判断当前级别下组织机构名称 是否相同
        Map map = new HashMap();
        map.put("parentOrgId",organization.getParentOrgId());
        map.put("orgName",organization.getOrgName());
        List<Organization>orgList = organizationMapper.findList(map);
        if(orgList!=null&&orgList.size()>0){
            respMap.put("flag",false);
            respMap.put("msg","message.org.orgname.existed");
            return respMap;
        }
        //校验通过
        String orgCode = StringUtil.getPinYinHeadChar(organization.getOrgName()).toUpperCase();
        Date now = new Date();
        String orgId = CreateIDUtil.getId();
        organization.setCreateTime(now);
        organization.setOrgId(orgId);
        organization.setOrgCode(orgCode);
        if(StringUtil.isNotEmpty(organization.getOrgPath())) {
            organization.setOrgPath(organization.getOrgPath() + "|" + orgId);
        }
        int row = organizationMapper.insertSelective(organization);
        if(row>0){
            respMap.put("flag",true);
            respMap.put("msg","message.system.save.success");
        }else{
            respMap.put("flag",false);
            respMap.put("msg","message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public Map getOrgById(String orgId)throws Exception {
        Map org = organizationMapper.findById(orgId);
        return org;
    }

    @Override
    public Map updateOrgnization(Organization organization)throws Exception {
        Map respMap = new HashMap();
        //没有上级组织
        if(StringUtil.isEmpty(organization.getParentOrgId())){
            organization.setParentOrgId("#");
        }
        //判断当前级别下组织机构名称 是否存在 相同
        Map map = new HashMap();
        map.put("parentOrgId",organization.getParentOrgId());
        map.put("orgName",organization.getOrgName());
        List<Organization>orgList = organizationMapper.findList(map);
        boolean flag = false;
        for (Organization org:orgList) {
            if(!org.getOrgId().equals(organization.getOrgId())){
                flag=true;
            }
        }
        if(flag){
            respMap.put("flag",false);
            respMap.put("msg","message.org.orgname.existed");
            return respMap;
        }
        //校验通过
        Date now =new Date();
        organization.setUpdateTime(now);
        //修改了上级组织
        if(StringUtil.isNotEmpty(organization.getOrgPath())) {
            organization.setOrgPath(organization.getOrgPath() + "|" + organization.getOrgId());
            //修改其所有子节点的orgPath
            Map sParam = new HashMap();
            sParam.put("orgPath",organization.getOrgId());
            //查询出所有子节点
            List<Organization>sonOrgList =organizationMapper.findList(sParam);
            for (Organization org: sonOrgList) {
                if(org.getOrgId().equals(organization.getOrgId())){
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                String[]sonPathArr = org.getOrgPath().split("\\|");
                for(int i= 0;i<sonPathArr.length;i++){
                    if(sonPathArr[i].equals(organization.getOrgId())){
                        sb.append(organization.getOrgPath());
                        for(int j = i+1 ; j<sonPathArr.length;j++){
                            sb.append("|");
                            sb.append(sonPathArr[j]);
                        }
                        break;
                    }
                }
                org.setOrgPath(sb.toString());

            }
            //批量修改子节点 orgPath
            if(sonOrgList!=null&&sonOrgList.size()>0){
                organizationMapper.batchUpdate(sonOrgList);
            }
        }else{
            organization.setOrgPath(null);
        }
        //修改组织机构
        int row = organizationMapper.updateByPrimaryKeySelective(organization);
        if(row>0){
            respMap.put("flag",true);
            respMap.put("msg","message.system.update.success");
        }else{
            respMap.put("flag",false);
            respMap.put("msg","message.system.update.fail");
        }
        return respMap;
    }

    @Override
    public List<Organization> getOrgListByOrgId(String orgId, String companyCode)throws Exception {
        List<Organization> list = organizationMapper.getOrgListByOrgId(orgId, companyCode);
        return list;
    }

    @Override
    public Map updateOrgStatus(Organization org) throws Exception{
        Date now = new Date();
        Map respMap = new HashMap();
        if(org.getStatus()==0){
            Map map = new HashMap();
            map.put("status",1);
            map.put("orgPath",org.getOrgId());
            List<Organization> orgList = organizationMapper.findList(map);
            if(orgList!=null&&orgList.size()>1){
                respMap.put("flag",false);
                respMap.put("msg","message.org.sub.notStop");
                return respMap;
            }
        }
        org.setUpdateTime(now);
        int row=organizationMapper.updateByPrimaryKeySelective(org);
        if(row<1){
            respMap.put("flag",false);
            respMap.put("msg","message.system.operation.fail");
        }else{
            respMap.put("flag",true);
            respMap.put("msg","message.system.operation.success");
        }
        return respMap;
    }

    @Override
    public List<Organization> getOrgListByMap(Map map) throws Exception{
        return organizationMapper.getOrgListByMap(map);
    }

}