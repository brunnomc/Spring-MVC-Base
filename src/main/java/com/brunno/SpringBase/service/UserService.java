package com.brunno.SpringBase.service;

import com.brunno.SpringBase.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User findUserByUsername(String username){
        User user = new User();
        user.setId(1L);
        user.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
        user.setUsername("admin");

        return user;
    }
}
