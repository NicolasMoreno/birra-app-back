package com.birraapp.birraappbackend.indicators.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MetricDTO {

    private List<DataDTO> data;

}
