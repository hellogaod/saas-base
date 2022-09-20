package com.base.saas.system.mapper;


import com.base.saas.system.model.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Title :
 * Description : 企业信息管理
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
public interface CompanyMapper {
    //根据id删除
    int deleteByPrimaryKey(String companyCode);

    //插入数据
    int insert(Company record);

    //插入数据
    int insertSelective(Company record);

    //根据主键id查询
    Company selectByPrimaryKey(String companyCode);

    //更新数据
    int updateByPrimaryKeySelective(Company record);

    //更新数据
    int updateByPrimaryKey(Company record);

    List<Company> getCompanyList(Company company);

    List<Company> getAllCompanyList();
}