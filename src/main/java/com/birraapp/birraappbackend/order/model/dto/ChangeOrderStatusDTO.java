package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangeOrderStatusDTO {

    private Long customerOrderId;
    private Double data;
    private OrderProcess process;
    private OrderState state;

}
