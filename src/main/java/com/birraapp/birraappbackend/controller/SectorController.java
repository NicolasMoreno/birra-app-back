package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.sector.SectorService;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@RequestMapping("/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @PostMapping("/")
    public ResponseEntity addSector(@RequestBody SectorModel sectorModel) {
        return ResponseEntity.ok(sectorService.saveSector(sectorModel));
    }

    @PutMapping("/")
    public ResponseEntity updateSector(@RequestBody SectorModel sectorModel) {
        return ResponseEntity.ok(sectorService.saveSector(sectorModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity findSector(@PathVariable Long id) {
        final Optional<SectorModel> sectorById = sectorService.findSectorById(id);
        if (sectorById.isPresent()) return ResponseEntity.ok(sectorById.get());
        else return ResponseEntity.status(400).body("No se encontró sector");
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }
}
