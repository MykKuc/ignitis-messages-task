package com.mykolas.ignitismessagetask.user;

import lombok.Builder;
@Builder
public class User {

    private long id;
    private String email;
    private  String name;
    private String password;
    private String token;
    private String role;
    private Boolean isactive;

    public User() {
    }

    public User(long id, String email, String name, String password, String token, String role, Boolean isactive) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.token = token;
        this.role = role;
        this.isactive = isactive;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Boolean getActive() {
        return isactive;
    }

    public void setActive(Boolean active) {
        isactive = active;
    }
}
