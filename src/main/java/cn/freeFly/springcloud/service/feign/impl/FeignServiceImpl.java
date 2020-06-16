package cn.freeFly.springcloud.service.feign.impl;

import cn.freeFly.springcloud.entity.BaseResponse;
import cn.freeFly.springcloud.feign.UserServiceFeignClient;
import cn.freeFly.springcloud.service.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignServiceImpl implements FeignService {
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Override
    public BaseResponse queryFeignList() {
        return userServiceFeignClient.queryUserList();
    }
}
