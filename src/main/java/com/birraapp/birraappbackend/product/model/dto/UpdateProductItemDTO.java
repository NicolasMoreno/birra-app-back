package com.birraapp.birraappbackend.product.model.dto;

import com.birraapp.birraappbackend.product.model.ProductItem;
import com.birraapp.birraappbackend.product.model.ProductModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductItemDTO extends ProductItemDTO {

    private Long id;
    private Long productId;

    public UpdateProductItemDTO(Long id, Long productId, CreateMaterialDTO material, Double quantity) {
        super(material, quantity);
        this.id = id;
        this.productId = productId;
    }

    @Override
    public ProductItem toModel(ProductModel product) {
        return super.toModel(product);
    }
}
