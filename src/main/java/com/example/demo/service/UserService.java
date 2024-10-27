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
    public void save(User user) {
        User savedUser = userRepository.save(user);
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
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
