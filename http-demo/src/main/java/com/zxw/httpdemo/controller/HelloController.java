package com.zxw.httpdemo.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zxw.httpdemo.client.UserClient;
import com.zxw.httpdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@DefaultProperties(defaultFallback = "hystrixCallBack") // 类共享服务超市配置
public class HelloController {
    @Autowired
    DiscoveryClient discoveryClient;

//    @Autowired
//    RibbonLoadBalancerClient client;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @RequestMapping("/hello")
    public ServiceInstance hello() {
        List<ServiceInstance> list = discoveryClient.getInstances("user-service");
        ServiceInstance instance = list.get(0);
        return instance;
    }

    @RequestMapping("/ribbon")
    public User ribbon(@PathVariable("id") Integer id) {
        String url = "http://user-service/user/" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    @GetMapping("/hello/{id}")
//    @HystrixCommand
    public User feign(@PathVariable("id") Integer id) {
        return userClient.hello(id);
    }
//    }  @GetMapping("/hello/{id}")
////    @HystrixCommand
//    public Map<String,Object> hystrix(@PathVariable("id")Integer id){
//        Map<String,Object> map = new HashMap<>();
//        String url = "http://laptop-6lr17gsr:8080/hello/" + id;
//        System.out.println(url);
//        User user = restTemplate.getForObject(url, User.class);
//        map.put("user",user);
//        return map;
//    }

        public Map<String, Object> hystrixCallBack () {
            Map<String, Object> map = new HashMap<>();
            map.put("errmsg", "服务器拥挤");
            return map;
        }

    }
