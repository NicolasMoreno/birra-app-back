package com.birraapp.birraappbackend;

import com.birraapp.birraappbackend.product.model.UnitModel;
import com.birraapp.birraappbackend.product.model.dto.CreateMaterialDTO;
import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.ProductItemDTO;

public class TestUtils {

    public static ProductItemDTO generateProductItem(CreateMaterialDTO material, Double quantity) {
        return new ProductItemDTO(material, quantity);
    }

    public static CreateProductDTO generateProduct(String name, String description, ProductItemDTO ...productItems) {
        return new CreateProductDTO(name, description, productItems);
    }

    public static CreateMaterialDTO generateMaterial(String materialName, UnitModel unit) {
        return new CreateMaterialDTO(
                materialName, unit
        );
    }
}
