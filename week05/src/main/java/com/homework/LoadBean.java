package com.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadBean {
    /**
     * 自定义Java代码装配
     */
    @Autowired
    private User user;
    /**
     * 通过xml方式装配
     */
    @Autowired
    private Car car;

    /**
     * 组件扫描和自动装配
     */
    @Autowired
    private Animal animal;

    public String load() {
        return "user: " + user.getName() + ", car: " + car.getName() + ", animal: " + animal.getName();
    }
}
