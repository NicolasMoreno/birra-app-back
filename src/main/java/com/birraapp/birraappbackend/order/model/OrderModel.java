package com.birraapp.birraappbackend.order.model;

import com.birraapp.birraappbackend.order.model.dto.CreateSubOrderDTO;
import com.birraapp.birraappbackend.order.model.dto.UpdateOrderDTO;
import com.birraapp.birraappbackend.product.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "customer_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = ProductModel.class,optional = false,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @OneToMany(targetEntity = SubOrderModel.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SubOrderModel> subOrders;

    private OrderState state;

    private Date startedDate;
    private Date finishedDate;
    private Integer orderAmount;
//    private Date estimatedFinishDate;
    private String description;

    public void addSubOrder(SubOrderModel subOrder) {
        subOrders.add(subOrder);
    }

    public UpdateOrderDTO toDTO() {
        final Set<CreateSubOrderDTO> subOrderSet = subOrders.stream().map(SubOrderModel::toDTO).collect(Collectors.toSet());
        return new UpdateOrderDTO(
                product.toDTO(), subOrderSet, state, startedDate, orderAmount, description, id
        );
    }
    // todo hacer cambiar el estado a la siguiente suborden y setearle la fecha afin a la suborden finalizada y cambiarle el estado

}
