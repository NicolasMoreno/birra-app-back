package com.birraapp.birraappbackend.stock;

import com.birraapp.birraappbackend.stock.model.StockModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<StockModel, Long> {

    Optional<StockModel> findByMaterial_Id(Long materialId);
}
