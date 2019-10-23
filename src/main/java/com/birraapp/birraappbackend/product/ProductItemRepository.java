package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.ProductItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductItemRepository extends CrudRepository<ProductItem, Long> {

    List<ProductItem> findByProduct_Id(Long productId);
}
