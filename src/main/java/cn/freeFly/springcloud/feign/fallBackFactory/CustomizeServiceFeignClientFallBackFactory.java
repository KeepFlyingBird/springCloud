package cn.freeFly.springcloud.feign.fallBackFactory;

import cn.freeFly.springcloud.dto.BaseResponse;
import cn.freeFly.springcloud.feign.CustomizeServiceFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomizeServiceFeignClientFallBackFactory implements FallbackFactory<CustomizeServiceFeignClient> {
    @Override
    public CustomizeServiceFeignClient create(Throwable cause) {
        return new CustomizeServiceFeignClient() {
            @Override
            public BaseResponse queryCustomizeUserList() {
                log.info("自定义容错："+cause.toString());
                return BaseResponse.failed(cause.toString());
            }
        };
    }
}
