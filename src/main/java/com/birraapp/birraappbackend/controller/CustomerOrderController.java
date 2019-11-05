package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.order.OrderService;
import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.dto.CreateOrderDTO;
import com.birraapp.birraappbackend.order.model.dto.ChangeOrderStatusDTO;
import com.birraapp.birraappbackend.order.model.dto.UpdateOrderDTO;
import com.birraapp.birraappbackend.product.ProductService;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.stock.model.dto.RequestOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CustomerOrderController {

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
        final List<UpdateOrderDTO> responseList = new ArrayList<>();
        orderService.getAll().iterator().forEachRemaining(elem -> responseList.add(elem.toDTO()));
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }

    @PostMapping("/change-process-status")
    public ResponseEntity startOrderProcess(@RequestBody ChangeOrderStatusDTO changeOrderStatusDTO) {
        final Optional<OrderModel> optionalOrder = orderService.findOrderById(changeOrderStatusDTO.getCustomerOrderId());
        if (optionalOrder.isPresent()) {
            final OrderModel orderModel = orderService.updateOrderProcess(optionalOrder.get().toDTO(), changeOrderStatusDTO);
            return ResponseEntity.ok(orderModel.toDTO());
        } else {
            return ResponseEntity.badRequest().body("Orden no se encontró");
        }
    }
}
