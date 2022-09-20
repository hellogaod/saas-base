package com.base.saas.system.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: PeiShouWen
 * @Description: 线程池配置
 * @Date 15:55 2017/7/24
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;

    @Value("${threadPool.maxPoolSize}")
    private int maxPoolSize;

    @Value("${threadPool.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Value("${threadPool.queueCapacity}")
    private int queueCapacity;

    @Value("${threadPool.threadNamePrefix}")
    private String threadNamePrefix;

    @Value("${threadPool.allowCoreThreadTimeout}")
    private Boolean allowCoreThreadTimeout;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最小数量
        taskExecutor.setCorePoolSize(corePoolSize);
        //线程池维护线程的最大数量
        taskExecutor.setMaxPoolSize(maxPoolSize);
        //空闲线程的存活时间
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        //队列最大长度
        taskExecutor.setQueueCapacity(queueCapacity);
        //线程名称前缀
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        //超过等待时间线程退出
        taskExecutor.setAllowCoreThreadTimeOut(allowCoreThreadTimeout);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 异常处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
