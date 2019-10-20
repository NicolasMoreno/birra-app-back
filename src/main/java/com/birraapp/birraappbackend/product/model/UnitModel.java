package com.birraapp.birraappbackend.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitModel {

    @Id
    @NotNull
    private String id;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    private String abbreviation;

}
