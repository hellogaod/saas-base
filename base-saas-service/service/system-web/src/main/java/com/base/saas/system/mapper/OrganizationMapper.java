package com.base.saas.system.mapper;


import com.base.saas.system.model.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 组织架构管理
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Mapper
public interface OrganizationMapper {
    int deleteByPrimaryKey(String orgId);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKeyWithBLOBs(Organization record);

    int updateByPrimaryKey(Organization record);

    //查询所有数据
    List<Organization> findList(Map map);

    //根据id关联查询父类
    Map findById(String orgId);

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 15:54
     * @Params: orgId 组织id
     * @Description: 获取组织及所有下级
     * @return: List
     */
    List<Organization> getOrgListByOrgId(@Param("orgId") String orgId, @Param("companyCode") String companyCode);

    /**
     * 批量修改
     * @param list
     * @return
     */
    int batchUpdate(List<Organization> list);

    List<Organization> getOrgListByMap(Map map);
}