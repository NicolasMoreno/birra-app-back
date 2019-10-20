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

    @ManyToOne(optional = false, targetEntity = UnitModel.class)
    private UnitModel unit;

    public MaterialModel(String name, UnitModel unit) {
        this.name = name;
        this.unit = unit;
    }

    public UpdateMaterialDTO toDTO() {
        return new UpdateMaterialDTO(
                name, id, unit
        );
    }
}
