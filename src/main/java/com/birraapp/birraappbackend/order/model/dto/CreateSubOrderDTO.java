package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubOrderDTO {
    private UpdateEmployeeDTO author;
    private OrderState state;
    private UpdateOrderDTO customerOrder;
    private OrderProcess orderProcess;
    private String name;
    private String description;
    private Date startedDate;
    private Date finishedDate;

    public SubOrderModel toModel() {
        final EmployeeModel employeeModel = author == null ? null : author.toModel(); // todo recibir de algún lado el intérprete del proceso. supongo que se lo updatea cuando se inicia el proceso
        return new SubOrderModel(
                null, employeeModel,
                state, null,
                orderProcess, name, description,
                startedDate, finishedDate
        );
    }
}
