package com.base.saas.manage.mapper.enterprise;


<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntDictConfig;
=======
import com.base.saas.manage.model.enterprise.EntDictConfig;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title :
 * Description : 字典管理
 */
@Mapper
public interface EntDictConfigMapper {

    //插入数据
    int insertSelective(EntDictConfig record);

    //根据主键id查询
    EntDictConfig selectByPrimaryKey(String id);

    //更新数据
    int updateByPrimaryKeySelective(EntDictConfig record);

    //查询数据列表
    List<EntDictConfig> findList(@Param("status") int status, @Param("companyCode") String companyCode, @Param("itemCode") String itemCode, @Param("itemName") String itemName);

}