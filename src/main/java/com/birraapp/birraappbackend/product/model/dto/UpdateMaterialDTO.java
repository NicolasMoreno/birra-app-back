package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.MaterialModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMaterialDTO extends CreateMaterialDTO {

    private Long id;

    public UpdateMaterialDTO(String name, Long id) {
        super(name);
        this.id = id;
    }

    @Override
    public MaterialModel toModel() {
        final MaterialModel material = super.toModel();
        material.setId(id);
        return material;
    }
}
