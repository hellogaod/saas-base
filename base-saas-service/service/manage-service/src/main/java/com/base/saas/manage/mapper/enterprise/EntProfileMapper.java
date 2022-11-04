package com.base.saas.manage.mapper.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntProfile;
=======
import com.base.saas.manage.model.enterprise.EntProfile;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntProfileMapper {
    //查询公司简介信息
    EntProfile getInfo(String companyCode);

    //插入一条公司简介信息
    int insert(EntProfile record);

    //更改一条公司简介信息
    int update(EntProfile companyProfile);
}