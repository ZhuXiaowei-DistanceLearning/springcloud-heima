package com.zxw.httpdemo.client;

import com.zxw.httpdemo.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {
    @Override
    public User hello(Integer id) {
        User user = new User();
        user.setName("熔断机制");
        return user;
    }
}
