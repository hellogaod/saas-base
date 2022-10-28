package com.base.saas.manage.mapper.enterprise;

import com.base.saas.manage.model.enterprise.EntUserMenuDesensite;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntUserMenuDesensiteMapper {

    //插入成功
    int insertSelective(EntUserMenuDesensite record);

    //修改成功
    int updateByPrimaryKeySelective(EntUserMenuDesensite record);

    //通过用户id或menuId获取当前表信息
    String getIdByUserIdAndMenuId(EntUserMenuDesensite userDataPermisson);

}