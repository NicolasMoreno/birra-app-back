package com.birraapp.birraappbackend.indicators;

import com.birraapp.birraappbackend.indicators.model.dto.DataDTO;
import com.birraapp.birraappbackend.indicators.model.dto.MetricDTO;
import com.birraapp.birraappbackend.order.OrderService;
import com.birraapp.birraappbackend.order.model.OrderModel;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import com.birraapp.birraappbackend.order.model.SubOrderModel;
import com.birraapp.birraappbackend.order.model.dto.UpdateOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IndicatorsService {

    @Autowired
    private OrderService orderService;

    public MetricDTO getTemperatureMetrics(OrderProcess process) {
        final List<OrderModel> orders = new ArrayList<>();
        final Map<LocalDate, List<SubOrderModel>> map = new HashMap<>();
        orderService.getAll().forEach(orders::add);
        orders.stream().collect(Collectors.groupingBy(order -> LocalDate.of(1900 + order.getStartedDate().getYear(),
                order.getStartedDate().getMonth() + 1,
                order.getStartedDate().getDate()))).forEach((localDate, orderModels) -> {
            final List<SubOrderModel> subOrders = orderModels
                    .stream()
                    .map(orderModel -> orderModel.getSubOrders().stream().filter(subOrderModel -> subOrderModel.getOrderProcess() == process).findFirst().get())
                    .collect(Collectors.toList());
            map.put(localDate, subOrders);
        }); //todo nefastisimo esto
        final List<DataDTO> data = new ArrayList<>();
        map.forEach( ((localDate, subOrderModels) -> data.add(
                DataDTO.builder()
                        .date(localDate)
                        .value(subOrderModels
                                .stream().filter(subOrderModel -> subOrderModel.getAdditionalData() != null)
                                .mapToDouble(SubOrderModel::getAdditionalData)
                                .average()
                                .getAsDouble()
                        ).build()
        )));
        return MetricDTO.builder()
                .data(data).build();
    }
}
