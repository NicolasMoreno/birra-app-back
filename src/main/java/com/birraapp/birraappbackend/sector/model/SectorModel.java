package com.birraapp.birraappbackend.sector.model;

import com.birraapp.birraappbackend.profile.model.ProfileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sectors")
@NoArgsConstructor
public class SectorModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "sectors")
    private Set<ProfileModel> profiles = new HashSet<>();

    public SectorModel(String name, Set<ProfileModel> profiles) {
        this.name = name;
        this.profiles = profiles;
    }
}
