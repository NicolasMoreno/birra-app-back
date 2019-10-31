package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.stock.model.dto.RequestOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/check")
    public ResponseEntity checkProduct(@RequestBody RequestOrderDTO requestOrderDTO) {
        final boolean isAvailable = productService.checkProductAvailability(requestOrderDTO.getProductId(), requestOrderDTO.getOrderAmount());
        return ResponseEntity.ok(isAvailable);
    }

    @GetMapping("/{productId}/max")
    public ResponseEntity getMaxProduct(@PathVariable Long productId){
        return ResponseEntity.ok("hola");
    }
}