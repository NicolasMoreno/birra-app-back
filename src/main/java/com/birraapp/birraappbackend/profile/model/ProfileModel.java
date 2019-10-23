package com.birraapp.birraappbackend.profile.model;

import com.birraapp.birraappbackend.profile.model.dto.UpdateProfileDTO;
import com.birraapp.birraappbackend.sector.model.SectorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class ProfileModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "profile_sector",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id")
    )
    private Set<SectorModel> sectors = new HashSet<>();

    public UpdateProfileDTO toDTO() {
        final UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO(
                name, id
        );
        updateProfileDTO.setSectors(sectors);
        return updateProfileDTO;
    }
}
