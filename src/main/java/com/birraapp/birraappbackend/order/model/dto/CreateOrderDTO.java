package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import com.birraapp.birraappbackend.order.model.utils.SubOrdersManager;
import com.birraapp.birraappbackend.product.model.UnitModel;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    private UpdateProductDTO product;
    private Set<CreateSubOrderDTO> subOrders;
    private OrderState state;
    private Date startedDate;
    private Date finishedDate;
    private Integer orderAmount;
    private String description;

    public static CreateOrderDTO startNewOrder(UpdateProductDTO product, Integer orderAmount, String description, Iterable<UnitModel> allUnits) {
        List<UnitModel> unitList = new ArrayList<>();
        allUnits.iterator().forEachRemaining(unitList::add);
        return new CreateOrderDTO(
                product, SubOrdersManager.buildNewSubOrders(unitList),
                OrderState.NO_EMPEZADO, new Date(),null,
                orderAmount, description
        );
    }

    public OrderModel toModel() {
        final Set<SubOrderModel> subOrderModelStream = subOrders.stream().map(CreateSubOrderDTO::toModel).collect(Collectors.toSet());
        return new OrderModel (
                null, product.toModel(), subOrderModelStream,
                state, startedDate, finishedDate, orderAmount, description
        );
    }


}
