package com.nyanja.nyanja_ecommerce_backend.controller;

import com.nyanja.nyanja_ecommerce_backend.model.User;
import com.nyanja.nyanja_ecommerce_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user, @RequestParam String confirmPass) {
        try {
            if (!user.getPassword().equals(confirmPass)) {
                return ResponseEntity.badRequest().body("Password Mismatches !");
            }
            if (userService.emailExists(user.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Email already Registered !");
            }
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String hashedPassword = encoder.encode(user.getPassword());
//            user.setPassword(hashedPassword);
            User savedUser = userService.createUser(user);
            if (savedUser != null) {
                return ResponseEntity.ok("Registration done Successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An expected error occurred!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User logUser) {
        try {
            User user = userService.emailExists(logUser.getEmail());
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            if (!encoder.matches(password, user.getPassword())) {
//                return ResponseEntity.badRequest().body("Invalid password");
//            }
            return ResponseEntity.ok("Login successful!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error!");
        }
    }
}
