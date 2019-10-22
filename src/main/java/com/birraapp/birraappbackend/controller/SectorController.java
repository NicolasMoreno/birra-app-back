package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.sector.SectorService;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @PostMapping("/")
    public ResponseEntity addSector(@RequestBody SectorModel sectorModel) {
        return ResponseEntity.ok(sectorService.saveSector(sectorModel));
    }
}
