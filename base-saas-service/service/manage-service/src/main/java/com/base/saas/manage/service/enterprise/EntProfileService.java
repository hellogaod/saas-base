package com.base.saas.manage.service.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntProfile;
=======
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntProfile;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

public interface EntProfileService {
    //查询公司简介
    EntProfile getInfo(String companyCode) throws Exception;

    //保存公司简介，保存机制：先把之前的公司简介状态设置为1.再把当前公司简介插入并且状态设置为0
    ReturnMap saveCompanyProfile(EntProfile companyProfile) throws Exception;


}
