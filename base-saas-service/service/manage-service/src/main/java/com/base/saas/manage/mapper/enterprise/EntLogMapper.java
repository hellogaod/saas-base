package com.base.saas.manage.mapper.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntLog;
import com.base.saas.manage.domain.model.EntLogRequest;
=======
import com.base.saas.common.logger.EntLog;
import com.base.saas.manage.model.EntLogRequest;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntLogMapper {
    //添加一条日志
    void addEntLog(EntLog sysWebLog) throws Exception;

    //日志列表
    List<EntLog> getEntLogList(EntLogRequest sysWebLog) throws Exception;

}
