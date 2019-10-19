package com.birraapp.birraappbackend.user.model;

import com.birraapp.birraappbackend.user.model.dto.UpdateUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

//    @OneToOne(mappedBy = "user_id",
//            fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL)
//    private EmployeeModel employee;

    public UserModel(String id, String username, String name, String lastName, String mail, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public UpdateUserDTO toDTO() {
        return new UpdateUserDTO(
                id, username, name, lastName, mail, password
        );
    }
}
