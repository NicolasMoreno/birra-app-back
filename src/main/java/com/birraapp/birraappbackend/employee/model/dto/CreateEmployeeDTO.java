package com.birraapp.birraappbackend.employee.model.dto;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.user.dto.CreateUserDTO;
import com.birraapp.birraappbackend.user.dto.UpdateUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeDTO {

    CreateUserDTO user;
    // UpdateProfileDTO profile;

    public CreateEmployeeDTO(CreateUserDTO user) {
        this.user = user;
    }

    public EmployeeModel toModel() {
        return new EmployeeModel(null, user.toModel());
    }


}
