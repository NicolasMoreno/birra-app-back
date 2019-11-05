package com.birraapp.birraappbackend.order.model.dto;

import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.product.model.dto.UpdateProductDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateOrderDTO extends CreateOrderDTO {

    private Long id;
    private OrderProcess actualProcess;

    public UpdateOrderDTO(UpdateProductDTO product, Set<CreateSubOrderDTO> subOrders, OrderState state, Date startedDate, Integer orderAmount, String description, Long id) {
        super(product, subOrders, state, startedDate, null, orderAmount, description);
        this.id = id;
        this.actualProcess = actualOrderProcess();
    }

    public OrderModel toModel() {
        final OrderModel orderModel = super.toModel();
        orderModel.setId(id);
        return orderModel;
    }

    public CreateSubOrderDTO getProcess(OrderProcess orderProcess) {
        final Optional<CreateSubOrderDTO> first = super.getSubOrders().stream().filter(process -> process.getOrderProcess() == orderProcess).findFirst();
        return first.orElse(null);
    }

    public CreateSubOrderDTO actualSubOrder() {
        final Optional<CreateSubOrderDTO> first = super.getSubOrders().stream().filter(process -> process.getState() == OrderState.EN_PROGRESO || process.getState() == OrderState.EMITIDO).findFirst();
        return first.orElse(getProcess(OrderProcess.MOLIENDA));
    }

    public OrderProcess actualOrderProcess() {
        return actualSubOrder().getOrderProcess();
    }

    public OrderState getState() {
        final boolean b = super.getSubOrders().stream().allMatch(createSubOrderDTO -> createSubOrderDTO.getState() == OrderState.NO_EMPEZADO);
        if (b) {
            return OrderState.NO_EMPEZADO;
        } else {
            if (getProcess(OrderProcess.GASIFICADO).getState() == OrderState.FINALIZADO) return OrderState.FINALIZADO;
            else return OrderState.EN_PROGRESO;
        }
    }

    public void changeSubOrderStatus(OrderProcess orderProcess, Double data, OrderState state) {
        final CreateSubOrderDTO process = getProcess(orderProcess);
        if (process != null) {
            process.changeSubOrderStatus(data, state);
            if (state == OrderState.FINALIZADO) armNextProcess(orderProcess);
            if (process.getState() == OrderState.EN_PROGRESO) super.setState(OrderState.EN_PROGRESO);
            else if (process.getState() == OrderState.FINALIZADO && process.getOrderProcess() == OrderProcess.GASIFICADO) super.setState(OrderState.FINALIZADO);
        }
    }

    private void armNextProcess(OrderProcess orderProcess) {
        switch (orderProcess) {
            case MOLIENDA: getProcess(OrderProcess.MACERADO).setState(OrderState.EMITIDO); break;
            case MACERADO: getProcess(OrderProcess.RECIRCULADO_LAVADO).setState(OrderState.EMITIDO); break;
            case RECIRCULADO_LAVADO: getProcess(OrderProcess.HERVIDO).setState(OrderState.EMITIDO); break;
            case HERVIDO: getProcess(OrderProcess.ENFRIADO).setState(OrderState.EMITIDO); break;
            case ENFRIADO: getProcess(OrderProcess.FERMENTACION).setState(OrderState.EMITIDO); break;
            case FERMENTACION: getProcess(OrderProcess.MADURADO).setState(OrderState.EMITIDO); break;
            case MADURADO: getProcess(OrderProcess.EMBOTELLADO).setState(OrderState.EMITIDO); break;
            case EMBOTELLADO: getProcess(OrderProcess.GASIFICADO).setState(OrderState.EMITIDO); break;
        }
    }

}
