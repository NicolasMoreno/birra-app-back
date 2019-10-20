package com.birraapp.birraappbackend.product.model;

import com.birraapp.birraappbackend.product.model.dto.CreateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.ProductItemDTO;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductItemDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_material")
public class ProductItem{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialModel material;

    private Double quantity;

    public ProductItem(ProductModel product, MaterialModel material, Double quantity) {
        this.product = product;
        this.material = material;
        this.quantity = quantity;
    }

    public UpdateProductItemDTO toDTO() {
        return new UpdateProductItemDTO(
                id,
                product.getId(),
                material.toDTO(),
                quantity
        );
    }




}
