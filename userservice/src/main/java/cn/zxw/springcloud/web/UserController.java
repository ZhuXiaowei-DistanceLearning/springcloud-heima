package cn.zxw.springcloud.web;

import cn.zxw.springcloud.pojo.User;
import cn.zxw.springcloud.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
public class UserController {
    @Auto
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @RequestMapping("hello/{id}")
    public User hello(@PathVariable("id") Long id) {
        System.out.println("----------执行了");
        User u = userService.getUser(id);
        return u;
    }
}
