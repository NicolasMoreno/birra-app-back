package com.birraapp.birraappbackend.product.model;


import com.birraapp.birraappbackend.product.model.dto.ProductItemDTO;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        final Set<ProductItemDTO> items = materials.stream().map(ProductItem::toDTO).collect(Collectors.toSet());
        return new UpdateProductDTO(
                id, name, description, items.toArray(new ProductItemDTO[0])
        );
    }
}
