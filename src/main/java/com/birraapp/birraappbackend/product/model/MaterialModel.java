package com.birraapp.birraappbackend.product.model;

import com.birraapp.birraappbackend.product.model.dto.UpdateMaterialDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "materials")
@Getter
@Setter
@NoArgsConstructor
public class MaterialModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    public MaterialModel(String name) {
        this.name = name;
    }

    public UpdateMaterialDTO toDTO() {
        return new UpdateMaterialDTO(
                name, id
        );
    }
}
