package com.homework;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource(locations = {"classpath:myApplicationContext.xml"})
@PropertySource("classpath:myApp.properties")
public class MyConfig {

    @Bean
    public User getUser() {
        User u = new User();
        u.setName("zhang_san");
        return u;
    }
}
