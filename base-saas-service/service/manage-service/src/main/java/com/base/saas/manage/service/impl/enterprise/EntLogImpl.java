package com.base.saas.manage.service.impl.enterprise;

import com.base.saas.manage.mapper.enterprise.EntLogMapper;

import com.base.saas.manage.domain.model.EntLogRequest;
import com.base.saas.manage.service.enterprise.EntLogService;
import com.base.saas.manage.domain.entity.enterprise.EntLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName EntLogImpl
 * @Description
 */
@Service
public class EntLogImpl implements EntLogService {
    @Autowired
    private EntLogMapper sysWebLogMapper;
    @Override
    public void addEntLogInfo(EntLog sysWebLog) throws Exception {
        if (sysWebLog!=null){
            Date now = new Date();
            sysWebLog.setCreateTime(now);
            sysWebLog.setOperateTime(new Date());
            sysWebLog.setIsDelete(0);
            sysWebLogMapper.addEntLog(sysWebLog);
        }else{
            throw new Exception();
        }
    }

    @Override
    public List<EntLog> getEntLogList(EntLogRequest sysWebLog) throws Exception {
        return sysWebLogMapper.getEntLogList(sysWebLog);
    }

}
