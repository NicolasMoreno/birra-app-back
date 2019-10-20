package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.MaterialModel;
import com.birraapp.birraappbackend.product.model.UnitModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMaterialDTO {
    private String name;
    private UnitModel unit;

    public CreateMaterialDTO(String name, UnitModel unit) {
        this.name = name;
        this.unit = unit;
    }

    public MaterialModel toModel() {
        return new MaterialModel(name, unit);
    }
}
