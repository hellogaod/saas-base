package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.manage.mapper.EntUserMapper;
import com.base.saas.manage.service.enterprise.EntUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年06月11日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class EntUserServiceImpl implements EntUserService {
    @Autowired
    private EntUserMapper entUserMapper;
    @Override
    public Map getUserByCompanyCode(String companyCode) {
        return entUserMapper.getUserByCompanyCode(companyCode);
    }
}
