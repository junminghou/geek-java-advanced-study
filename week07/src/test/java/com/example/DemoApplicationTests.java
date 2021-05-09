package com.example;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void queryUsers() {
        // QueryWrapper<User> wrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(null);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void addUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(10 + i);
            user.setName("test_shardingsphere_" + i);
            userMapper.insert(user);
        }
    }
}
