package com.example.driveronboardservice.service;

import com.example.driveronboardservice.exception.SignUpException;
import com.example.driveronboardservice.model.User;
import com.example.driveronboardservice.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        try{
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new SignUpException(e.getCause().getCause().getMessage());
        }catch (Exception e){
            throw new SignUpException(e.getMessage());
        }

    }

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
