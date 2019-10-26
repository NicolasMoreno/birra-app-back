package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.UnitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UnitRepository extends CrudRepository<UnitModel, Integer> {

    Optional<UnitModel> getByUnitName(String unitName);
}
