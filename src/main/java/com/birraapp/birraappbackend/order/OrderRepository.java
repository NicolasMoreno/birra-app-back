package com.birraapp.birraappbackend.order;

import com.birraapp.birraappbackend.order.model.OrderModel;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderModel, Long> {
}
