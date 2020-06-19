package cn.freeFly.springcloud.service.feign.impl;

import cn.freeFly.springcloud.dto.BaseResponse;
import cn.freeFly.springcloud.feign.CustomizeServiceFeignClient;
import cn.freeFly.springcloud.feign.UserServiceFeignClient;
import cn.freeFly.springcloud.service.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignServiceImpl implements FeignService {
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;
    @Autowired
    private CustomizeServiceFeignClient customizeServiceFeignClient;
    @Override
    public BaseResponse queryFeignList() {
        BaseResponse response = null;
//        response = userServiceFeignClient.queryUserList();
        response = customizeServiceFeignClient.queryCustomizeUserList();
        return response;
    }
}
