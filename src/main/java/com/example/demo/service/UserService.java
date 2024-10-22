package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public String handleHello(){
        return "Hello World! from service";
    }
}
