package com.base.saas.manage.mapper.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntRole;
=======
import com.base.saas.manage.model.enterprise.EntRole;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntRoleMapper {

    //插入一条角色信息
    int insertSelective(EntRole record);

    //修改角色信息
    int updateByPrimaryKeySelective(EntRole record);

    //获取角色信息
    EntRole selectByPrimaryKey(String roleId);

    //获取角色列表
    List<EntRole> getRoleList(@Param("status") int status,@Param("roleName") String roleName,@Param("companyCode") String companyCode);

}