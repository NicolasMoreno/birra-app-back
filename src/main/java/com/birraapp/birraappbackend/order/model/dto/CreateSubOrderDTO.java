package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.employee.model.dto.UpdateEmployeeDTO;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import com.birraapp.birraappbackend.product.model.UnitModel;
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
    private OrderProcess orderProcess;
    private Date startedDate;
    private Date finishedDate;
    private Double initialData;
    private Double finishData;
    private UnitModel unit;
    private Double additionalData;

    public SubOrderModel toModel() {
        final EmployeeModel employeeModel = author == null ? null : author.toModel(); // todo recibir de algún lado el intérprete del proceso. supongo que se lo updatea cuando se inicia el proceso
        return new SubOrderModel(
                null, employeeModel,
                state,
                orderProcess,
                startedDate, finishedDate, initialData, finishData, unit, additionalData
        );
    }

    public void changeSubOrderStatus(Double data, Double additionalData, OrderState status) {
        if (status == OrderState.EN_PROGRESO) {
            startSubOrder(data);
        } else if (status == OrderState.FINALIZADO) {
            finishSubOrder(data);
        }
        setAdditionalData(additionalData);
    }

    private void startSubOrder(Double initialData) {
        setState(OrderState.EN_PROGRESO);
        setInitialData(initialData);
        setStartedDate(new Date());
    }

    private void finishSubOrder(Double finishData) {
        setState(OrderState.FINALIZADO);
        setFinishData(finishData);
        setFinishedDate(new Date());
    }
}
