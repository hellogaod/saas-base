package com.base.saas.manage.service.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.enterprise.EntUser;
=======
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntModule;
import com.base.saas.manage.model.enterprise.EntUser;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

import java.util.List;

/**
 * Title :
 * Description : 企业用户登录
 */
public interface EntLoginService {

    //企业用户登录
    ReturnMap<EntUser> entLogin(String account, String password, String companyCode) throws Exception;

    //获取当前用户拥有的模块信息，以及模块下菜单信息
    List<EntModule> getModuleAndMenuList(String userId, String companyCode) throws Exception;

    //企业用户最后一次登录的ip地址
    void updateEntLoginIpInfo(String userId, String companyCode, String remoteAdrr);

    //企业用户修改密码
    ReturnMap updateComUserPwd(String account, String companyCode, String oldPwd, String newPwd) throws Exception;

}
