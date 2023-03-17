package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User user);
    User getUserById(long id);
}
