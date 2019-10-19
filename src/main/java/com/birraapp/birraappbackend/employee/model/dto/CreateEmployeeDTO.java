package com.birraapp.birraappbackend.employee.model.dto;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import com.birraapp.birraappbackend.user.model.dto.CreateUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeDTO {

    CreateUserDTO user;
    CreateProfileDTO profile;

    public CreateEmployeeDTO(CreateUserDTO user, CreateProfileDTO profile) {
        this.user = user;
        this.profile = profile;
    }

    public EmployeeModel toModel() {
        return new EmployeeModel(null, user.toModel(), profile.toModel());
    }


}
