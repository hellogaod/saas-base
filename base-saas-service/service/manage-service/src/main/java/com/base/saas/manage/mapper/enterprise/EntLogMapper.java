package com.base.saas.manage.mapper.enterprise;

import com.base.saas.common.logger.EntLog;
import com.base.saas.manage.model.EntLogRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntLogMapper {
    //添加一条日志
    void addEntLog(EntLog sysWebLog) throws Exception;

    //日志列表
    List<EntLog> getEntLogList(EntLogRequest sysWebLog) throws Exception;

}
