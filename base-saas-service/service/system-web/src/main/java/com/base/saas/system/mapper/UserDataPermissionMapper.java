package com.base.saas.system.mapper;

import com.base.saas.system.model.UserDataPermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDataPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDataPermission record);

    int insertSelective(UserDataPermission record);

    UserDataPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDataPermission record);

    int updateByPrimaryKeyWithBLOBs(UserDataPermission record);

    int updateByPrimaryKey(UserDataPermission record);

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 15:58
     * @Params: userDataPermisson
     * @Description: 获取数据权限
     * @return: UserDataPermission
     */
    UserDataPermission getUserDataPermisson(UserDataPermission userDataPermisson);

    String getIdByUserIdAndMenuId(UserDataPermission userDataPermisson);

    int updateUserData(UserDataPermission record);
}