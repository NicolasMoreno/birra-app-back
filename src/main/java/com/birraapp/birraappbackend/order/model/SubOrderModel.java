package com.birraapp.birraappbackend.order.model;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.order.model.dto.UpdateSubOrderDTO;
import com.birraapp.birraappbackend.product.model.UnitModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sub_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOrderModel {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeModel responsible; // TODO ver estos

    private OrderState state;

    private OrderProcess orderProcess;

//    private String name; // Me parece que este no va
//    private String description; // Me parece que este no va

    private Date startedDate;
    private Date finishedDate;

    private Double initialData; // dato inicial para la métrica
    private Double finishData; //  dato final para la métrica

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitModel unit;
    private Double additionalData;

    public UpdateSubOrderDTO toDTO() {
        return new UpdateSubOrderDTO(
                null, state, orderProcess,
                startedDate, finishedDate, id, initialData, finishData, unit, additionalData
        ); // customerOrder.toDTO(),
    }

}
