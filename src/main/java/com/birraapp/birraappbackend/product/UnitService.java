package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.UnitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public UnitModel saveUnit(UnitModel unitModel) {
        return unitRepository.save(unitModel);
    }

    public Optional<UnitModel> findUnit(Integer unitId) {
        return unitRepository.findById(unitId);
    }

    public Optional<UnitModel> findUnitByUnitName(String unitName) {
        return unitRepository.getByUnitName(unitName.toUpperCase());
    }

    public Iterable<UnitModel> getAllUnits() {
        return unitRepository.findAll();
    }
}
