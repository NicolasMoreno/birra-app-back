package com.birraapp.birraappbackend.product.model;

import com.birraapp.birraappbackend.product.model.dto.UpdateProductItemDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_material")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ProductItem{

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
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
