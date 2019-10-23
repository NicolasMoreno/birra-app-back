package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateOrderDTO extends CreateOrderDTO {

    private Long id;

    public UpdateOrderDTO(UpdateProductDTO product, Set<CreateSubOrderDTO> subOrders, OrderState state, Date startedDate, Integer orderAmount, String description, Long id) {
        super(product, subOrders, state, startedDate, orderAmount, description);
        this.id = id;
    }

    public OrderModel toModel() {
        final OrderModel orderModel = super.toModel();
        orderModel.setId(id);
        return orderModel;
    }


}
