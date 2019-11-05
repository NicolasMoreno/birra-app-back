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
                             OrderProcess orderProcess,
                             String name, String description, Date startedDate,
                             Date finishedDate, Long id, Double initialData, Double finishData) {
        super(author, state,
                orderProcess, name, description,
                startedDate, finishedDate, initialData, finishData);
        this.id = id;
    }

    public SubOrderModel toModel() {
        final SubOrderModel subOrderModel = super.toModel();
        subOrderModel.setId(id);
        return subOrderModel;
    }
}
