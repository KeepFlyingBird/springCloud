package cn.freeFly.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {cn.freeFly.springcloud.annotationInterface.FeignExcludeComponent.class}))
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
