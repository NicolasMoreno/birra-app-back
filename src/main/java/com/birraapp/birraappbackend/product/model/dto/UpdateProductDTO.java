package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.ProductModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO extends CreateProductDTO {

    private Long id;

    public UpdateProductDTO(Long id, String name, String description, UpdateProductItemDTO ...productItems) {
        super(name, description, productItems);
        this.id = id;
    }

    @Override
    public ProductModel toModel() {
        final ProductModel productModel = super.toModel();
        productModel.setId(id);
        return productModel;
    }
}
