package com.birraapp.birraappbackend.stock.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {

    private Long productId;
    private Integer orderAmount;
    private String description;
}
