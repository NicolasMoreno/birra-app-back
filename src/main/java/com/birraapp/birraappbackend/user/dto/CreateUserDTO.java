package com.birraapp.birraappbackend.user.dto;

import com.birraapp.birraappbackend.user.model.UserModel;

public class CreateUserDTO {

    private String username;
    private String name;
    private String lastName;
    private String mail;
    private String password;

    public CreateUserDTO(String username, String name, String lastName, String mail, String password) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public UserModel toModel() {
        return new UserModel( null,
                this.username, this.name, this.lastName, this.mail, this.password
        );
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
