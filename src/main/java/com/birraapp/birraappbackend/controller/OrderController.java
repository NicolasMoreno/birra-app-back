package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.order.OrderService;
import com.birraapp.birraappbackend.order.model.dto.CreateOrderDTO;
import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.stock.model.dto.RequestOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity addOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return ResponseEntity.ok(orderService.addOrder(createOrderDTO));
    }

    @PostMapping("/start")
    public ResponseEntity startNewOrder(@RequestBody RequestOrderDTO requestOrderDTO) {
        final Optional<ProductModel> productToProduce = productService.findProductById(requestOrderDTO.getProductId());
        if (!productToProduce.isPresent()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no encontrado para producir");
        return ResponseEntity.ok(orderService.createNewOrder(productToProduce.get(), requestOrderDTO));
    }

    @GetMapping("/all")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }
}
