package cn.freeFly.springcloud.feign;

import cn.freeFly.springcloud.entity.BaseResponse;
import cn.freeFly.springcloud.feign.fallBackFactory.UserServiceFeignClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-user", url = "http://localhost:8081/jy-project/", fallbackFactory = UserServiceFeignClientFallBackFactory.class)
public interface UserServiceFeignClient {
    /**
     * 查询所有用户
     * @return
     */
    // SpringMVC 锲约
    @GetMapping(value = "yqFinancial/YqCompensatory!queryCompensatoryDataList.action")
    public BaseResponse queryUserList();
}
