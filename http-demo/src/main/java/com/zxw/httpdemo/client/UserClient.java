package com.zxw.httpdemo.client;

import com.zxw.httpdemo.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "user-service", fallback = UserClientImpl.class)
public interface UserClient {

    @GetMapping("hello/{id}")
    public User hello(@PathVariable("id")Integer id);
}
