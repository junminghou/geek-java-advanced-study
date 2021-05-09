package com.jm.dynamicDataSource.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jm.dynamicDataSource.config.DataSourceNames;
import com.jm.dynamicDataSource.config.MultiDataSource;
import com.jm.dynamicDataSource.entity.User;
import com.jm.dynamicDataSource.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> getAllUserListByFirstDb() {
        return this.baseMapper.selectList(null);
    }

    @MultiDataSource(name = DataSourceNames.SECOND)
    @Override
    public List<User> getAllUserListBySecondDb() {
        return this.baseMapper.selectList(null);
    }

    @MultiDataSource(name = DataSourceNames.SECOND)
    @Override
    public void insertUserBySecondDb(User user) {
        this.baseMapper.insert(user);
    }

    @MultiDataSource(name = DataSourceNames.FIRST)
    @Override
    public void insertUserByFirstDb(User user) {
        this.baseMapper.insert(user);
    }
}
