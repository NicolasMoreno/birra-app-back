package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.indicators.IndicatorsService;
import com.birraapp.birraappbackend.order.model.OrderProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/metrics")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MetricsController {

    @Autowired
    private IndicatorsService indicatorsService;

    @GetMapping("/temperature/{orderProcess}")
    public ResponseEntity getTemperatureMetrics(@PathVariable OrderProcess orderProcess) {
        return ResponseEntity.ok(indicatorsService.getTemperatureMetrics(orderProcess));
    }
}
