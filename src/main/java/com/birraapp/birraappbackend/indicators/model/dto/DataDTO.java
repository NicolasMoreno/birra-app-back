package com.birraapp.birraappbackend.indicators.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class DataDTO {

    private LocalDate date;
    private Double value;

}
