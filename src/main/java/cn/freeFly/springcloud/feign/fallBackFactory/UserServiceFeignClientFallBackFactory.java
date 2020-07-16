package cn.freeFly.springcloud.feign.fallBackFactory;

import cn.freeFly.springcloud.dto.BaseResponse;
import cn.freeFly.springcloud.feign.UserServiceFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class UserServiceFeignClientFallBackFactory implements FallbackFactory<UserServiceFeignClient> {

    @Override
    public UserServiceFeignClient create(Throwable cause) {
        return new UserServiceFeignClient() {
            @Override
            public BaseResponse queryUserList() {
                log.info("容错：" + cause.toString());
                return BaseResponse.failed(cause.toString());
            }
        };
    }
}
