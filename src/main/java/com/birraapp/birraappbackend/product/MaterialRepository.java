package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.MaterialModel;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository extends CrudRepository<MaterialModel, Long> {
}
