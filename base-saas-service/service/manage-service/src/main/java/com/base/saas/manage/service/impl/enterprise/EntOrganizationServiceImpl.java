package com.base.saas.manage.service.impl.enterprise;


import com.base.saas.manage.mapper.enterprise.EntOrganizationMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntOrganization;
import com.base.saas.manage.service.enterprise.EntOrganizationService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;

import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Title :
 * Description : @组织架构管理@
 */
@Service
public class EntOrganizationServiceImpl implements EntOrganizationService {

    @Resource
    private EntOrganizationMapper organizationMapper;

    @Override
    public List<EntOrganization> getOrgList(String companyCode,
                                            String orgName,
                                            String orgCode,
                                            String parentOrgId,
                                            int orgType,
                                            int status) throws Exception {
        //查询所有组织管理
        List<EntOrganization> orgList = organizationMapper.findList(companyCode,
                orgName,
                orgCode,
                parentOrgId,
                orgType,
                status);


        return getTreeOrganizations(orgList);
    }

    // 获取组织架构的树形结构
    private List<EntOrganization> getTreeOrganizations(List<EntOrganization> orgList) {
        return orgList.stream().
                filter(orgItem -> "#".equals(orgItem.getParentOrgId()))
                .peek(
                        orgItem -> orgItem.setChildren(getChildrens(orgItem, orgList))
                )
                .collect(Collectors.toList());
    }

    private List<EntOrganization> getChildrens(EntOrganization root, List<EntOrganization> organizations) {

        List<EntOrganization> list = organizations.stream()
                .filter(item ->
                        //筛选出下一层元素节点
                        Objects.equals(item.getParentOrgId(), root.getOrgId())
                )
                .map(item ->
                {
                    //递归set子节点
                    item.setChildren(this.getChildrens(item, organizations));
                    return item;
                })
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public ReturnMap saveOrgnization(EntOrganization organization) throws Exception {
        ReturnMap respMap = new ReturnMap();
        //没有上级组织
        if (StringUtil.isEmpty(organization.getParentOrgId())) {
            organization.setParentOrgId("#");
        }
        //判断当前级别下组织机构名称 是否相同
        List<EntOrganization> orgList = organizationMapper.findList(null,
                organization.getOrgName(),
                null,
                organization.getParentOrgId(),
                -1,
                -1);
        if (orgList != null && orgList.size() > 0) {
            respMap.setMsg("message.org.orgname.existed");
            return respMap;
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        organization.setCreateUser(userInfo.getAccount());//创建人
        organization.setUpdateUser(userInfo.getAccount());
        organization.setCompanyCode(userInfo.getCompanyCode());//公司编码

        //校验通过
        String orgCode = StringUtil.getPinYinHeadChar(organization.getOrgName()).toUpperCase();
        Date now = new Date();
        String orgId = CreateIDUtil.getId();
        organization.setCreateTime(now);
        organization.setUpdateTime(now);
        organization.setOrgId(orgId);
        organization.setOrgCode(orgCode);

        int row = organizationMapper.insertSelective(organization);
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.save.success");
        } else {
            respMap.setMsg("message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public EntOrganization getOrgById(String orgId) throws Exception {
        return organizationMapper.findById(orgId);
    }

    @Override
    public ReturnMap updateOrgnization(EntOrganization organization) throws Exception {
        ReturnMap respMap = new ReturnMap();
        //没有上级组织
        if (StringUtil.isEmpty(organization.getParentOrgId())) {
            organization.setParentOrgId("#");
        }
        //判断当前级别下组织机构名称 是否存在 相同

        List<EntOrganization> orgList = organizationMapper.findList(null,
                organization.getOrgName(),
                null,
                organization.getParentOrgId(),
                -1,
                -1);

        boolean flag = false;
        for (EntOrganization org : orgList) {
            if (!org.getOrgId().equals(organization.getOrgId())) {
                flag = true;
            }
        }
        if (flag) {
            respMap.setMsg("message.org.orgname.existed");
            return respMap;
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        organization.setUpdateUser(userInfo.getAccount());//修改人
        organization.setCompanyCode(userInfo.getCompanyCode());//公司编码
        //校验通过
        Date now = new Date();
        organization.setUpdateTime(now);

        //修改组织机构
        int row = organizationMapper.updateByPrimaryKeySelective(organization);
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.update.success");
        } else {
            respMap.setMsg("message.system.update.fail");
        }
        return respMap;
    }


    @Override
    public ReturnMap updateOrgStatus(EntOrganization org) throws Exception {
        ReturnMap respMap = new ReturnMap();

        if (org.getStatus() == 0) {//暂停当前部门，首先确保当前部门下的子部门全部暂停

            List<EntOrganization> orgList = organizationMapper.findList(null,
                    org.getOrgName(),
                    null,
                    org.getOrgId(),
                    -1,
                    1);
            if (orgList != null && orgList.size() > 1) {
                respMap.setMsg("message.org.sub.notStop");
                return respMap;
            }
        }

        Date now = new Date();
        UserInfo userInfo = UserContextUtil.getUserInfo();
        //修改人
        org.setUpdateUser(userInfo.getAccount());
        org.setUpdateTime(now);
        int row = organizationMapper.updateByPrimaryKeySelective(org);
        if (row < 1) {
            respMap.setMsg("message.system.operation.fail");
        } else {
            respMap.setCode(1);
            respMap.setMsg("message.system.operation.success");
        }
        return respMap;
    }

}