package com.base.saas.manage.mapper.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.enterprise.EntUser;
=======
import com.base.saas.manage.model.enterprise.EntMenu;
import com.base.saas.manage.model.enterprise.EntUser;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

/**
 * Title :
 * Description : 企业用户登录
 */
@Mapper
public interface EntLoginMapper {
    //当前用户拥有的所有权限
    List<EntMenu> getUserPermissions(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("moduleId") String moduleId);

    //获取企业用户信息
    EntUser getEntUser(@Param("account") String account, @Param("companyCode") String companyCode);

    //修改用户最后一次登录的ip地址
    int updateEntUserIpInfo(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("remoteAddr") String remoteAddr);

    //如果当前用户不是锁定状态，那么修改status：如果status ！=-1，错误次数+1
    void lockComUser(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("status") int status);

    //状态设置1，错误次数设为0，最后一次锁定时间设为null
    void clearEntUserErrCount(@Param("userId") String userId, @Param("companyCode") String companyCode);

    //修改密码
    int updateEntUserPwd(@Param("userId") String userId, @Param("companyCode") String companyCode, @Param("newPwd") String newPwd);


}
