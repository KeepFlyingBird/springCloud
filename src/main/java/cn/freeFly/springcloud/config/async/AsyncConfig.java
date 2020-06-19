package cn.freeFly.springcloud.config.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassNmae AsyncConfig
 * @Author xiao.yunfei
 * @Date 2019/11/24 16:13
 * @Desc 定义线程池 和 启用异步
 */
@Configuration
@EnableAsync // 该注解代表开启Spring异步
public class AsyncConfig implements AsyncConfigurer {
    private final Logger log = LoggerFactory.getLogger(AsyncConfig.class);
    @Override
    @Bean(name = "taskExecutor")
    public TaskExecutor getAsyncExecutor() {
        log.info("Creating Async Task Executor");
        // 自定义线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(10);
        // 线程池最大线程数
        taskExecutor.setMaxPoolSize(30);
        // 线程队列最大线程数
        taskExecutor.setQueueCapacity(200);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(60);
        // 指定用于新创建的线程名称的前缀
        taskExecutor.setThreadNamePrefix("springboot2-async-");
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
