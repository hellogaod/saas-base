package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.EntModuleMapper;
import com.base.saas.manager.service.EntModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class EntModuleServiceImpl implements EntModuleService {
    @Autowired
    private EntModuleMapper entModuleMapper;
    @Override
    public List getModuleByCompanyCode(String companyCode) throws Exception{
        return entModuleMapper.getModuleByCompanyCode(companyCode);
    }
}
