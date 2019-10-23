package com.birraapp.birraappbackend.order;

import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.dto.CreateOrderDTO;
import com.birraapp.birraappbackend.order.model.dto.UpdateOrderDTO;
import com.birraapp.birraappbackend.product.model.ProductModel;
import com.birraapp.birraappbackend.stock.model.dto.RequestOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderModel addOrder(CreateOrderDTO createOrderDTO) {
        return orderRepository.save(createOrderDTO.toModel());
    }

    public OrderModel updateOrder(UpdateOrderDTO updateOrderDTO) {
        return orderRepository.save(updateOrderDTO.toModel());
    }

    public Optional<OrderModel> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public boolean deleteOrder(UpdateOrderDTO order) {
        orderRepository.delete(order.toModel());
        return true;
    }

    public OrderModel createNewOrder(ProductModel productModel, RequestOrderDTO requestOrderDTO) {
        return addOrder(CreateOrderDTO.startNewOrder(productModel.toDTO(), requestOrderDTO.getOrderAmount(), requestOrderDTO.getDescription()));
    }

    public Iterable<OrderModel> getAll() {
        return orderRepository.findAll();
    }
}
