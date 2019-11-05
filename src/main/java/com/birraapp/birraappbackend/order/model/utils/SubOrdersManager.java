package com.birraapp.birraappbackend.order.model.utils;

import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.dto.CreateSubOrderDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class SubOrdersManager {

    public static Set<CreateSubOrderDTO> buildNewSubOrders() {
        final HashSet<CreateSubOrderDTO> subOrders = new HashSet<>();
        subOrders.add(buildNewSubOrder(OrderProcess.MOLIENDA));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.MACERADO));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.RECIRCULADO_LAVADO));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.HERVIDO));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.ENFRIADO));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.FERMENTACION));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.MADURADO));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.EMBOTELLADO));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.GASIFICADO));
        return subOrders;
    }

    public static CreateSubOrderDTO buildNewSubOrder(OrderProcess process) {
        return new CreateSubOrderDTO(
                null, OrderState.EMITIDO, process, "", "", null, null, null,null
        );
    }

    public static CreateSubOrderDTO buildNonStartedSubOrder(OrderProcess process) {
        return new CreateSubOrderDTO(
                null, OrderState.NO_EMPEZADO, process, "", "", null, null, null,null
        );
    }
}
