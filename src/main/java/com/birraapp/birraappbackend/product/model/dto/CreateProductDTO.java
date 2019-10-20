package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.ProductItem;
import com.birraapp.birraappbackend.product.model.ProductModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateProductDTO {

    private String name;
    private String description;
    private Set<ProductItemDTO> materials;

    public CreateProductDTO(String name, String description, ProductItemDTO ...productItems) {
        this.name = name;
        this.description = description;
        materials = new HashSet<>();
        materials.addAll(Arrays.asList(productItems));
    }

    public ProductModel toModel() {
        final ProductModel productModel = new ProductModel(
                name,
                description
        );
        final Set<ProductItem> materials = this.materials
                .stream()
                .map(productItemDTO -> productItemDTO.toModel(productModel))
                .collect(Collectors.toSet());
        productModel.setMaterials(materials);
        return productModel;
    }
}
