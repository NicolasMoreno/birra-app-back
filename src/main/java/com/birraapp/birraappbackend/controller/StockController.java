package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.stock.model.dto.RequestOrderDTO;
import com.birraapp.birraappbackend.stock.model.dto.RestockDTO;
import com.birraapp.birraappbackend.stock.StockService;
import com.birraapp.birraappbackend.stock.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @PostMapping("/restock")
    public ResponseEntity restockMaterial(@RequestBody RestockDTO restockDTO) {
        final Optional<StockModel> stockModel = stockService.reStockMaterial(restockDTO.getMaterialId(), restockDTO.getRestockingAmount());
        if (stockModel.isPresent()) return ResponseEntity.ok(stockModel.get());
        else return ResponseEntity.status(404).body("Material no encontrado");
    }

    @PostMapping("/check")
    public ResponseEntity checkProduct(@RequestBody RequestOrderDTO requestOrderDTO) {
        final boolean isAvailable = productService.checkProductAvailability(requestOrderDTO.getProductId(), requestOrderDTO.getOrderAmount());
        return ResponseEntity.ok(isAvailable);
    }

    @GetMapping("/{materialId}")
    public ResponseEntity getMaterialStock(@PathVariable Long materialId) {
        return ResponseEntity.ok(stockService.findStockByMaterialId(materialId));
    }

    @GetMapping("/all")
    public ResponseEntity getAllMaterialStock() {
        return ResponseEntity.ok(stockService.getAllMaterials());
    }
}
