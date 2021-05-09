package com.jm.dynamicDataSource;

import com.jm.dynamicDataSource.entity.User;
import com.jm.dynamicDataSource.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void init() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(10 + i);
            user.setName("test_" + i);
            if (i % 2 == 0) {
                userService.insertUserByFirstDb(user);
            } else {
                userService.insertUserBySecondDb(user);
            }
        }
    }

    @Test
    void read() {
        List<User> user = userService.getAllUserListByFirstDb();
        log.info("第一个数据库 : ");
        user.forEach(System.out::println);

        List<User> user2 = userService.getAllUserListBySecondDb();
        log.info("第二个数据库 : ");
        user2.forEach(System.out::println);
    }



}
