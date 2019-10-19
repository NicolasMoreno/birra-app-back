package com.birraapp.birraappbackend.employee.model;


import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import com.birraapp.birraappbackend.user.dto.UpdateUserDTO;
import com.birraapp.birraappbackend.user.model.UserModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class EmployeeModel {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    //    private ProfileModel profile;

    public EmployeeModel(Long id, UserModel user) {
        this.id = id;
        this.user = user;
    }

    public UpdateEmployeeDTO toDTO() {
        return new UpdateEmployeeDTO(
                id, user.toDTO()
        );
    }

}
