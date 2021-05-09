package com.jm.dynamicDataSource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.dynamicDataSource.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getAllUserListByFirstDb();

    List<User> getAllUserListBySecondDb();

    void insertUserBySecondDb(User user);

    void insertUserByFirstDb(User user);
}
