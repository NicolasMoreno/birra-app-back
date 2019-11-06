package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import com.birraapp.birraappbackend.product.model.UnitModel;
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
                             OrderProcess orderProcess, Date startedDate,
                             Date finishedDate, Long id, Double initialData, Double finishData,
                             UnitModel unit, Double additionalData) {
        super(author, state,
                orderProcess,
                startedDate, finishedDate, initialData, finishData, unit, additionalData);
        this.id = id;
    }

    public SubOrderModel toModel() {
        final SubOrderModel subOrderModel = super.toModel();
        subOrderModel.setId(id);
        return subOrderModel;
    }
}
