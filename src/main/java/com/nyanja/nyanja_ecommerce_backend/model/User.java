package com.nyanja.nyanja_ecommerce_backend.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String names;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String password;
    @Transient
    private String confirmPass;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(UserStatus status) {
        this.status = status;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id, String names, String email, UserStatus status, String password, String confirmPass) {
        this.id = id;
        this.names = names;
        this.email = email;
        this.status = status;
        this.password = password;
        this.confirmPass=confirmPass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
