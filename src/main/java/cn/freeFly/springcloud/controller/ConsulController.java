package cn.freeFly.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Calendar;
import java.util.Calendar;
import java.util.List;

@RestController
public class ConsulController {
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     */
    @RequestMapping("/api/discoveryClient")
    public Object discover() {
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-producer");
        return instances;
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/api/loadBalancer")
    public Object loadBalancer() {
        ServiceInstance choose = loadBalancer.choose("consul-producer");
        URI uri = choose.getUri();
        return uri.toString();
    }

    @RequestMapping("/api/consulTest")
    public String consulTest() {
        return "hello,springCloud";
    }



}
