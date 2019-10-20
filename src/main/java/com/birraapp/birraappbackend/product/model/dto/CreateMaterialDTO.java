package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.MaterialModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMaterialDTO {
    private String name;

    public CreateMaterialDTO(String name) {
        this.name = name;
    }

    public MaterialModel toModel() {
        return new MaterialModel(name);
    }
}
