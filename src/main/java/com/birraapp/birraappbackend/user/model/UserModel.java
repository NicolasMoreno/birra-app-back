package com.birraapp.birraappbackend.user.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, updatable = false, name = "id")
    private String id;

    @Column(unique = true)
    private String username;

    private String name;
    private String lastName;

    @Column(unique = true)
    private String mail;

    private String password;

    public UserModel(String id, String username, String name, String lastName, String mail, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
