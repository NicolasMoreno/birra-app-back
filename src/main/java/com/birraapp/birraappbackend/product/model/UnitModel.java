package com.birraapp.birraappbackend.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    private String abbreviation;

    private QuantityType quantityType;

}
