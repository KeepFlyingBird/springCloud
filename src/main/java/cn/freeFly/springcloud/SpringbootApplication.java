package cn.freeFly.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {cn.freeFly.springcloud.annotationInterface.FeignExcludeComponent.class}))
@EnableSwagger2 //添加swagger启用注解
@MapperScan("cn.freeFly.springcloud.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
