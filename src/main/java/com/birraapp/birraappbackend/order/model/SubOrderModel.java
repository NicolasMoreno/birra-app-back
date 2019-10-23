package com.birraapp.birraappbackend.order.model;

import com.birraapp.birraappbackend.employee.model.EmployeeModel;
import com.birraapp.birraappbackend.order.model.dto.UpdateSubOrderDTO;
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
    private EmployeeModel author;

    private OrderState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_order_id")
    private OrderModel customerOrder;

    private OrderProcess orderProcess;

    private String name;
    private String description;

    private Date startedDate;
    private Date finishedDate;

    public UpdateSubOrderDTO toDTO() {
        return new UpdateSubOrderDTO(
                author.toDTO(), state, customerOrder.toDTO(), orderProcess,
                name, description, startedDate, finishedDate, id
        );
    }

}