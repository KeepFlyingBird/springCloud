package cn.freeFly.springcloud.feign;

import cn.freeFly.springcloud.config.feign.FeignClientCustomizeConfiguration;
import cn.freeFly.springcloud.dto.BaseResponse;
import cn.freeFly.springcloud.feign.fallBackFactory.CustomizeServiceFeignClientFallBackFactory;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "service-customize", url = "http://localhost:8081/jy-project/", fallbackFactory = CustomizeServiceFeignClientFallBackFactory.class
        , configuration = FeignClientCustomizeConfiguration.class)
public interface CustomizeServiceFeignClient {
    /**
     * 查询所有用户
     * @return
     */
    // Feign 锲约
    @RequestLine("GET yqFinancial/YqCompensatory!queryCompensatoryDataList.action")
    public BaseResponse queryCustomizeUserList();
}
