package com.base.saas.manage.service.enterprise;

<<<<<<< HEAD
import com.base.saas.manage.domain.entity.enterprise.EntLog;
import com.base.saas.manage.domain.model.EntLogRequest;
=======
import com.base.saas.common.logger.EntLog;
import com.base.saas.manage.model.EntLogRequest;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

import java.util.List;

public interface EntLogService {
    /**
     * 新增一条日志记录
     * @param sysWebLog
     * @throws Exception
     */
    public void addEntLogInfo(EntLog sysWebLog)throws Exception;

    /**
    *@Description 查询日志列表
    *@Param 
    *@return 
    *@Date
    **/
    public List<EntLog> getEntLogList(EntLogRequest sysWebLog)throws Exception;

}
