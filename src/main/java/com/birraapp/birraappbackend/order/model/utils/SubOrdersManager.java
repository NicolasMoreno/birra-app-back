package com.birraapp.birraappbackend.order.model.utils;

import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.OrderState;
import com.birraapp.birraappbackend.order.model.dto.CreateSubOrderDTO;
import com.birraapp.birraappbackend.product.model.UnitModel;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class SubOrdersManager {

    public static Set<CreateSubOrderDTO> buildNewSubOrders(List<UnitModel> unitList) {
        final UnitModel kilos = unitList.stream().filter(unit -> unit.getUnitName().equalsIgnoreCase("Kilo")).findFirst().get();
        final UnitModel celcius = unitList.stream().filter(unit -> unit.getUnitName().equalsIgnoreCase("Celcius")).findFirst().get();
        final HashSet<CreateSubOrderDTO> subOrders = new HashSet<>();
        subOrders.add(buildNewSubOrder(OrderProcess.MOLIENDA, kilos));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.MACERADO, celcius));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.RECIRCULADO_LAVADO, null));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.HERVIDO, celcius));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.ENFRIADO, celcius));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.FERMENTACION, celcius));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.MADURADO, celcius));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.EMBOTELLADO, null));
        subOrders.add(buildNonStartedSubOrder(OrderProcess.GASIFICADO, null));
        return subOrders;
    }

    public static CreateSubOrderDTO buildNewSubOrder(OrderProcess process, UnitModel unit) {
        return new CreateSubOrderDTO(
                null, OrderState.EMITIDO, process, null, null, null,null, unit, null
        );
    }

    public static CreateSubOrderDTO buildNonStartedSubOrder(OrderProcess process, UnitModel unit) {
        return new CreateSubOrderDTO(
                null, OrderState.NO_EMPEZADO, process,null, null, null,null, unit, null
        );
    }
}
