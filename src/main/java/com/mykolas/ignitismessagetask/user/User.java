package com.mykolas.ignitismessagetask.user;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private  String name;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;


    public User() {
    }

    public User(long id, String email, String name, String password, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
