package com.birraapp.birraappbackend.sector;

import com.birraapp.birraappbackend.sector.model.SectorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public SectorModel saveSector(SectorModel sector) {
        return sectorRepository.save(sector);
    }

    public List<SectorModel> saveManySector(List<SectorModel> sector) {
        List<SectorModel> resultList = new ArrayList<>();
        for (SectorModel sectorToSave: sector
             ) {
            resultList.add(sectorRepository.save(sectorToSave));
        }
        return resultList;
    }

    public void deleteSector(SectorModel sector) {
        sectorRepository.delete(sector);
    }
}
