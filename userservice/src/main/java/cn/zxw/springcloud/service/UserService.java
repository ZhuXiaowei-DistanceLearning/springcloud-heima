package cn.zxw.springcloud.service;

import cn.zxw.springcloud.mapper.UserMapper;
import cn.zxw.springcloud.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(Long id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        User user = userMapper.selectByPrimaryKey(id);
        System.out.println("----id:" + id);
        User u = new User();
        u.setId(id);
//        List<User> select = userMapper.select(u);
//        userMapper.selectOne(u);
        User user = userMapper.selectById(id);
        return userMapper.selectById(u);
    }
}
