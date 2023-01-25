package com.mykolas.ignitismessagetask.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserAddRequest {

    @NotBlank(message = "Name must not be null or blank.")
    @Length(min = 1, max = 40, message = "Incorrect length of new user name.")
    private String name;

    @NotBlank(message = "Email field can not be null or blank.")
    @Length(min = 1,max = 60, message = "Incorrect length of email.")
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$", message = "Incorrect provided email form.")
    private String email;

    @NotBlank(message = "Password can not be null or blank.")
    @Length(min = 1, max = 40, message = "Incorrect length of password.")
    private String password;

    public UserAddRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserAddRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
