package com.base.saas.manage.start;

import com.base.saas.common.start.StartAnno;
import com.base.saas.common.start.cache.AbsCacheLoader;
import com.base.saas.common.start.cache.CacheLoaderAnno;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Component
@StartAnno(description = "加载系统参数至Redis")
@CacheLoaderAnno(description = "加载系统参数至Redis")
@Service("sysParaCacheLoader")
public class SysParaCacheLoader extends AbsCacheLoader implements ApplicationRunner,Ordered {
    private static Logger log = LoggerFactory.getLogger(SysParaCacheLoader.class);
    @Autowired
    private SysParaMapper sysParaMapper;

    @Override
    public synchronized void loading() {
        log.info("开始加载系统参数至Redis");
        Map<String, String> paraMap = new HashMap(10);
        List<Map<String, String>> paraList = sysParaMapper.getSysPara();
        for (Map<String, String> para : paraList) {
            RedisUtil.set(RedisKeyConstants.SYS_PARA+ String.valueOf(para.get("paraName"))+ "_" + String.valueOf(para.get("companyCode")),  para);
        }
        log.info("结束加载系统参数至Redis");

    }

    @Override
    public synchronized void refresh() {
        clean();
        loading();
    }

    @Override
    public synchronized void clean() {
        log.info("clean：Redis缓存中系统参数===>start");
//        List<Map<String, String>> paraList = sysParaMapper.getSysPara();
//        for (Map<String, String> para : paraList) {
//            RedisUtil.del(RedisKeyConstants.SYS_PARA+ String.valueOf(para.get("paraName"))+ "_" + String.valueOf(para.get("companyCode")));
//        }
        log.info("clean：Redis缓存中系统参数===>end");
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        clean();
        loading();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
