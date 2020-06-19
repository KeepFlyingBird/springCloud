package cn.freeFly.springcloud.controller;

import cn.freeFly.springcloud.dto.BaseResponse;
import cn.freeFly.springcloud.service.feign.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feign")
@Slf4j
public class FeignController {
    @Autowired
    private FeignService feignService;

    @PostMapping("/queryList")
    public BaseResponse queryFeignList(){
        return feignService.queryFeignList();
    }
}
