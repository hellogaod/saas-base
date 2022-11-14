package com.base.saas.manage.service.enterprise;


import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntUser;


import java.util.List;

/**
 * Title :
 * Description :用户管理
 */
public interface EntUserService {

    /**
     * 获取用户列表
     */
    List<EntUser> getUserList(String companyCode,
                              int status,
                              String account,
                              String realName,
                              String tel) throws Exception;

    /**
     * 添加用户
     */
    ReturnMap addUser(EntUser user) throws Exception;

    /**
     * @Params: user
     * 用户实体
     * @Description: 启用，停用
     * @return:
     */
    ReturnMap updateState(EntUser user) throws Exception;

    /**
     * @Description: 重置密码
     */
    ReturnMap resetPassword(String userId) throws Exception;

    /**
     * @Params: userId
     * 用户id
     * @Description: 查询单个用户
     * @return:
     */
    EntUser getUserById(String userId);
    /**
     * 修改用户
     */
    ReturnMap updateUser(EntUser user) throws Exception;


}
