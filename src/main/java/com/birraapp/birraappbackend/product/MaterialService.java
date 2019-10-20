package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.MaterialModel;
import com.birraapp.birraappbackend.product.model.dto.CreateMaterialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public MaterialModel createMaterial(CreateMaterialDTO createMaterialDTO) {
        return materialRepository.save(createMaterialDTO.toModel());
    }

}
