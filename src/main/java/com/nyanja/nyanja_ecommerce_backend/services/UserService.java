package com.nyanja.nyanja_ecommerce_backend.services;

import com.nyanja.nyanja_ecommerce_backend.model.User;
import com.nyanja.nyanja_ecommerce_backend.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUser(String email, String password) {
       return userRepository.findUserByEmailAndPassword(email,password);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User emailExists(String email){
        return userRepository.findByEmail(email);
    }
    public void updateUser(long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
        }
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
