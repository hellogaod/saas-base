package com.base.saas.manage.service.enterprise;

import com.base.saas.manage.model.SysWebLogRequest;
import com.base.saas.common.logger.SysWebLog;

import java.util.List;

public interface SysWebLogService {
    /**
     * 新增一条日志记录
     * @param sysWebLog
     * @throws Exception
     */
    public void addSysWebLogInfo(SysWebLog sysWebLog)throws Exception;

    /**
    *@Description 查询日志列表
    *@Param 
    *@return 
    *@Author coder_bao
    *@Date  
    **/
    public List<SysWebLog> getSysWebLogList(SysWebLogRequest sysWebLog)throws Exception;

    /**
    *@Description 根据主键查询日志的异常堆栈信息
    *@Param 
    *@return 
    *@Author coder_bao
    *@Date  
    **/
    public String getExceptionStackMsgById(String id)throws Exception;
}
