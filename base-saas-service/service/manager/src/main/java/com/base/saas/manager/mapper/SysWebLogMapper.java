package com.base.saas.manager.mapper;

import com.base.saas.manager.model.SysWebLogRequest;
import com.base.saas.common.logger.SysWebLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysWebLogMapper {
    void addSysWebLogInfo(SysWebLog sysWebLog)throws Exception;

    List<SysWebLog> getSysWebLogList(SysWebLogRequest sysWebLog)throws Exception;

    String getExceptionStackMsgById(String id)throws Exception;
}
