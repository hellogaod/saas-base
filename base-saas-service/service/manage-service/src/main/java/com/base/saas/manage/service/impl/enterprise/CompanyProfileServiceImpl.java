package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.manage.mapper.CompanyProfileMapper;
import com.base.saas.manage.model.CompanyProfile;
import com.base.saas.manage.service.enterprise.CompanyProfileService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liangdeng
 * @Date: 2018/9/10
 * @Description:
 */
@Service
public class CompanyProfileServiceImpl implements CompanyProfileService {

    @Autowired
    private CompanyProfileMapper companyProfileMapper;

    @Override
    public Map saveCompanyProfile(CompanyProfile companyProfile) throws Exception{
        //先逻辑删除
        companyProfile.setDeleteFlag((short)1);
        companyProfileMapper.update(companyProfile);
        //在添加新的记录
        companyProfile.setId(CreateIDUtil.getId());
        companyProfile.setDeleteFlag((short)0);
        int count = companyProfileMapper.insert(companyProfile);
        /*String companyCode = companyProfile.getCompanyCode();
        CompanyProfile profile = companyProfileMapper.getInfo(companyCode);
        if(profile!=null){
            companyProfile.setDeleteFlag((short)1);
            count = companyProfileMapper.update(companyProfile);
        }else {
            companyProfile.setDeleteFlag((short)0);
            count = companyProfileMapper.insert(companyProfile);
        }*/
        Map resultMap = new HashMap();
        if (count==0){
            resultMap.put("flag",false);
            resultMap.put("msg","message.system.save.fail");
        }
        resultMap.put("flag",true);
        resultMap.put("msg","message.system.save.success");
        return resultMap;
    }

    @Override
    public CompanyProfile getInfo(String companyCode) throws Exception {
        return companyProfileMapper.getInfo(companyCode);
    }
}
