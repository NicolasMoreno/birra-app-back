package com.birraapp.birraappbackend.user.model.dto;

import com.birraapp.birraappbackend.user.model.UserModel;

public class UpdateUserDTO extends CreateUserDTO {

    private String id;

    public UpdateUserDTO(String id, String username, String name, String lastName, String mail, String password) {
        super(username, name, lastName, mail, password);
        this.id = id;
    }

    @Override
    public UserModel toModel() {
        return new UserModel(
                this.id,
                super.getUsername(),
                super.getName(),
                super.getLastName(),
                super.getMail(),
                super.getPassword()
        );
    }
}
