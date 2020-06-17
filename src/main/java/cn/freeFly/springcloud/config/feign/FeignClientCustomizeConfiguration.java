package cn.freeFly.springcloud.config.feign;

import cn.freeFly.springcloud.annotationInterface.FeignExcludeComponent;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@FeignExcludeComponent
@Configuration
public class FeignClientCustomizeConfiguration {
    @Bean
    public Contract feignContract(){
        /**
         * 默认使用SpringMVC 锲约  举个例子：@GetMapping (value = "/user/getUser/{id}")
         * 现指定为Feign 锲约      举个例子：@RequestLine("GET /user/getUser/{id}")
         */
        return new Contract.Default();
    }
    @Bean
    public Retryer feignRetryer(){
        /**
         * period=100 发起当前请求的时间间隔，单位毫秒
         * maxPeriod=1000 发起当前请求的最大时间间隔，单位毫秒
         * maxAttempts=3 最多请求次数，包括第一次
         */
        return new Retryer.Default(100,1000,3);
    }
    @Bean
    public Request.Options feignRequestOptions(){
        /**
         * connectTimeoutMillis：20000  #毫秒连接超时时间
         * readTimeoutMillis: 20000  #毫秒逻辑处理超时时间
         */
        return new Request.Options(20000,10000);
    }
    @Bean
    public Logger.Level feginLogLevel(){
        /**
         * 配置Client 日志打印级别
         * NONE, 不记录 (DEFAULT).
         * BASIC, 仅记录请求方式和URL及响应的状态代码与执行时间.
         * HEADERS, 日志的基本信息与请求及响应的头.
         * FULL, 记录请求与响应的头和正文及元数据
         */
        return Logger.Level.FULL;
    }
}
