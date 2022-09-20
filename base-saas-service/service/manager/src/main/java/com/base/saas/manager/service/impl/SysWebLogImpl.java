package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.SysWebLogMapper;
import com.base.saas.manager.model.SysWebLogRequest;
import com.base.saas.manager.service.SysWebLogService;
import com.base.saas.common.logger.SysWebLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysWebLogImpl
 * @Description
 * @Author coder_bao
 * @Date 2018/9/5 11:29
 */
@Service
public class SysWebLogImpl implements SysWebLogService {
    @Autowired
    private SysWebLogMapper sysWebLogMapper;
    @Override
    public void addSysWebLogInfo(SysWebLog sysWebLog) throws Exception {
        if (sysWebLog!=null){
            Date now = new Date();
            sysWebLog.setCreateTime(now);
            sysWebLog.setOperateTime(new Date());
            sysWebLog.setIsDelete(0);
            sysWebLogMapper.addSysWebLogInfo(sysWebLog);
        }else{
            throw new Exception();
        }
    }

    @Override
    public List<SysWebLog> getSysWebLogList(SysWebLogRequest sysWebLog) throws Exception {
        return sysWebLogMapper.getSysWebLogList(sysWebLog);
    }

    @Override
    public String getExceptionStackMsgById(String id) throws Exception {
        return sysWebLogMapper.getExceptionStackMsgById(id);
    }
}
