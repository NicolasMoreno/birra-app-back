package com.birraapp.birraappbackend.employee.model.dto;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.profile.model.dto.UpdateProfileDTO;
import com.birraapp.birraappbackend.user.model.dto.UpdateUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeDTO {

    private Long id;
    private UpdateUserDTO user;
    private UpdateProfileDTO profile;

    public UpdateEmployeeDTO(Long id, UpdateUserDTO user, UpdateProfileDTO profile) {
        this.id = id;
        this.user = user;
        this.profile = profile;
    }

    public EmployeeModel toModel() {
        return new EmployeeModel(id, user.toModel(), profile.toModel());
    }
}
