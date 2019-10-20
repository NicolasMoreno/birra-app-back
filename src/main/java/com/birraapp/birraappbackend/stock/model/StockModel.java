package com.birraapp.birraappbackend.stock.model;

import com.birraapp.birraappbackend.product.model.MaterialModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockModel {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(optional = false, targetEntity = MaterialModel.class)
    private MaterialModel material;

    private Double storedQuantity;

    public Double sumQuantity(Double toSumQuantity) {
        setStoredQuantity(storedQuantity + toSumQuantity);
        return storedQuantity;
    }

    public Double consumeQuantity(Double toConsumeQuantity) {
        setStoredQuantity(storedQuantity - toConsumeQuantity);
        return storedQuantity;
    }

    public boolean isStockAvailable(Double checkQuantity) {
        return storedQuantity >= checkQuantity;
    }
}
