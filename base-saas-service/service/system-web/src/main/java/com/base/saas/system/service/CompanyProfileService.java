package com.base.saas.system.service;

import com.base.saas.system.model.CompanyProfile;

import java.util.Map;

/**
 * @Auther: liangdeng
 * @Date: 2018/9/10
 * @Description:
 */
public interface CompanyProfileService {

    /**
     * @description: 保存公司简介
     * @param:
     * @return:
     * @auther: liangdeng
     * @date: 2018/9/10
     */
    Map saveCompanyProfile(CompanyProfile companyProfile) throws Exception;

    /**
     * @description: 获取公司简介
     * @param:
     * @return:
     * @auther: liangdeng
     * @date: 2018/9/10
     */
    CompanyProfile getInfo(String companyCode) throws Exception;
}
