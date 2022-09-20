package com.base.saas.manager.start;


import com.base.saas.manager.mapper.EntMenuMapper;
import com.base.saas.common.start.StartAnno;
import com.base.saas.common.start.cache.AbsCacheLoader;
import com.base.saas.common.start.cache.CacheLoaderAnno;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月25日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Component
@StartAnno(description = "加载菜单url至Redis")
@CacheLoaderAnno(description = "加载菜单url至Redis")
public class MenuCacheLoader extends AbsCacheLoader implements ApplicationRunner,Ordered {

    @Autowired
    private EntMenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public synchronized void loading() {
        List<Map<String, String>> urlList = menuMapper.getAllMenuUrl();
        Map<String, HashSet<String>> urlMap = new HashMap<>();
        for (Map<String, String> item : urlList) {
            String company_code = item.get("company_code");
            String url = item.get("url");
            if (!urlMap.containsKey(company_code)) {
                urlMap.put(company_code, new HashSet<>());
            }
            urlMap.get(company_code).add(url);
        }
        RedisUtil.set(RedisKeyConstants.MENU_LIST, urlMap);
    }

    @Override
    public synchronized void refresh() {
        clean();
        loading();
    }

    @Override
    public synchronized void clean() {
        RedisUtil.del(RedisKeyConstants.MENU_LIST);
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