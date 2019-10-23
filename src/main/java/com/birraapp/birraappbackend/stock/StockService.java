package com.birraapp.birraappbackend.stock;

import com.birraapp.birraappbackend.stock.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public StockModel saveStock(StockModel stockModel) {
        return stockRepository.save(stockModel);
    }

    public Optional<StockModel> findStockByMaterialId(Long materialId) {
        return stockRepository.findByMaterial_Id(materialId);
    }

    public Optional<StockModel> reStockMaterial(Long materialId, Double sumQuantity) {
        final Optional<StockModel> optionalStock = findStockByMaterialId(materialId);
        if (!optionalStock.isPresent()) return Optional.empty();
        else {
            final StockModel stock = optionalStock.get();
            stock.sumQuantity(sumQuantity);
            return Optional.of(stockRepository.save(stock));
        }
    }

    // TODO check unit test of this.
    public Optional<StockModel> consumeMaterial(Long materialId, Double consumeQuantity) {
        final Optional<StockModel> optionalStock = findStockByMaterialId(materialId);
        if (!optionalStock.isPresent()) return Optional.empty();
        else {
            final StockModel stock = optionalStock.get();
            stock.consumeQuantity(consumeQuantity);
            return Optional.of(stockRepository.save(stock));
        }
    }

    public boolean checkMaterialStock(Long materialId, Double totalAmount) {
        final Optional<StockModel> optionalStock = findStockByMaterialId(materialId);
        return optionalStock.map(stockModel -> stockModel.isStockAvailable(totalAmount)).orElse(false);
    }

    public Iterable<StockModel> getAllMaterials() {
        return stockRepository.findAll();
    }

    public Optional<StockModel> findStockById(Long stockId) {
        return stockRepository.findById(stockId);
    }
}
