package com.birraapp.birraappbackend.profile.model.dto;

import com.birraapp.birraappbackend.profile.model.ProfileModel;

public class UpdateProfileDTO extends CreateProfileDTO {

    private Long id;

    public UpdateProfileDTO(String name, Long id) {
        super(name);
        this.id = id;
    }

    public ProfileModel toModel() {
        return new ProfileModel(
                id, super.getName(), super.getSectors()
        );
    }
}
