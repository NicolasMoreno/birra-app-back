package com.birraapp.birraappbackend.product;

import com.birraapp.birraappbackend.product.model.UnitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public UnitModel saveUnit(UnitModel unitModel) {
        return unitRepository.save(unitModel);
    }

    public Optional<UnitModel> findUnit(String unitId) {
        return unitRepository.findById(unitId);
    }
}
