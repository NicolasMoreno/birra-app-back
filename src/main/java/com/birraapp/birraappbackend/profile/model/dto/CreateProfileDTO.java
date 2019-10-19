package com.birraapp.birraappbackend.profile.model.dto;

import com.birraapp.birraappbackend.profile.model.ProfileModel;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class CreateProfileDTO {
    private String name;
    private Set<SectorModel> sectors;

    public CreateProfileDTO(String name) {
        this.name = name;
        this.sectors = new HashSet<>();
    }

    public ProfileModel toModel() {
        return new ProfileModel(
                null, name, sectors
        );
    }
}
