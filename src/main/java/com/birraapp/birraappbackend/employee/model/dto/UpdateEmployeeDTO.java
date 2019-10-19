package com.birraapp.birraappbackend.employee.model.dto;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.user.dto.UpdateUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeDTO {

    private Long id;
    private UpdateUserDTO user;
    // private UpdateProfileDto profile;

    public UpdateEmployeeDTO(Long id, UpdateUserDTO user) {
        this.id = id;
        this.user = user;
    }

    public EmployeeModel toModel() {
        return new EmployeeModel(id, user.toModel());
    }
}
