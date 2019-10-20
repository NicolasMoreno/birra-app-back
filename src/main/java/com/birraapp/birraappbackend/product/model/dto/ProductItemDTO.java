package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.ProductItem;
import com.birraapp.birraappbackend.product.model.ProductModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductItemDTO {

    private CreateMaterialDTO material;
    private Double quantity;

    public ProductItemDTO(CreateMaterialDTO material, Double quantity) {
        this.material = material;
        this.quantity = quantity;
    }

    public ProductItem toModel(ProductModel product) {
        return new ProductItem(product, material.toModel(), quantity);
    }


}
