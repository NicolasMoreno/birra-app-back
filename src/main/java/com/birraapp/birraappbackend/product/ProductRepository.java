package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductModel, Long> {


}
