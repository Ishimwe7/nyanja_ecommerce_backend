package com.nyanja.nyanja_ecommerce_backend.repos;

import com.nyanja.nyanja_ecommerce_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
}
