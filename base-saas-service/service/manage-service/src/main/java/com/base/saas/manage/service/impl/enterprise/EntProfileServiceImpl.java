package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.manage.mapper.enterprise.EntProfileMapper;
<<<<<<< HEAD
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntProfile;
=======
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntProfile;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import com.base.saas.manage.service.enterprise.EntProfileService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntProfileServiceImpl implements EntProfileService {

    @Autowired
    private EntProfileMapper companyProfileMapper;

    @Override
    public ReturnMap saveCompanyProfile(EntProfile companyProfile) throws Exception{
        //先逻辑删除
        companyProfile.setDeleteFlag((short)1);
        companyProfileMapper.update(companyProfile);
        //在添加新的记录
        companyProfile.setId(CreateIDUtil.getId());
        companyProfile.setDeleteFlag((short)0);
        int count = companyProfileMapper.insert(companyProfile);

        ReturnMap resultMap = new ReturnMap();
        if (count==0){
            resultMap.setMsg("message.system.save.fail");
        }
        resultMap.setCode(1);
        resultMap.setMsg("message.system.save.success");
        return resultMap;
    }

    @Override
    public EntProfile getInfo(String companyCode) throws Exception {
        return companyProfileMapper.getInfo(companyCode);
    }
}
