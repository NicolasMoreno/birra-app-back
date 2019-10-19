package com.birraapp.birraappbackend.profile;

import com.birraapp.birraappbackend.profile.model.ProfileModel;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileModel saveProfile(CreateProfileDTO createProfileDTO) {
        return profileRepository.save(createProfileDTO.toModel());
    }

}
