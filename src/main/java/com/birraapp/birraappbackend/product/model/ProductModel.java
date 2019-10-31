package com.birraapp.birraappbackend.product.model;


import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductItemDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private Set<ProductItem> materials = new HashSet<>();

    public ProductModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductModel(String name, String description, Set<ProductItem> materials) {
        this.name = name;
        this.description = description;
        this.materials = materials;
    }

    public UpdateProductDTO toDTO() {
        return new UpdateProductDTO(
                id, name, description, materials.stream().map(ProductItem::toDTO).distinct().toArray(UpdateProductItemDTO[]::new)
        );
    }
}
