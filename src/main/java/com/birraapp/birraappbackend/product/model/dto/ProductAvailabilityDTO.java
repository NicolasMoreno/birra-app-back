package com.birraapp.birraappbackend.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAvailabilityDTO {

    private Long productId;
    private Integer productAvailability;

}
