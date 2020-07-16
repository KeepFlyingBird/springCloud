package cn.freeFly.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//feign
@EnableFeignClients
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {cn.freeFly.springcloud.annotationInterface.FeignExcludeComponent.class}))
//feign
@EnableSwagger2 //添加swagger启用注解
@MapperScan("cn.freeFly.springcloud.mapper")//mapper文件包扫描
@PropertySource(value = "classpath:rabbitMq.properties") //mq配置文件
//consul
@EnableDiscoveryClient
//consul
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
