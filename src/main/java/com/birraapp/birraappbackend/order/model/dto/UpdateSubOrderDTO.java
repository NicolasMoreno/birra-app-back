package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSubOrderDTO extends CreateSubOrderDTO {
    private Long id;
    public UpdateSubOrderDTO(UpdateEmployeeDTO author, OrderState state,
                             UpdateOrderDTO customerOrder, OrderProcess orderProcess,
                             String name, String description, Date startedDate,
                             Date finishedDate, Long id) {
        super(author, state, customerOrder,
                orderProcess, name, description,
                startedDate, finishedDate);
        this.id = id;
    }

    public void startSubOrder() {
        super.setState(OrderState.IN_PROGRESS);
        super.setStartedDate(new Date());
    }

    public void finishSubOrder() {
        super.setState(OrderState.FINISHED);
        super.setFinishedDate(new Date());
    }

    public SubOrderModel toModel() {
        final SubOrderModel subOrderModel = super.toModel();
        subOrderModel.setId(id);
        return subOrderModel;
    }
}
