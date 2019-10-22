package com.birraapp.birraappbackend.profile;

import com.birraapp.birraappbackend.profile.model.ProfileModel;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import com.birraapp.birraappbackend.profile.model.dto.UpdateProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileModel saveProfile(CreateProfileDTO createProfileDTO) {
        return profileRepository.save(createProfileDTO.toModel());
    }

    public Optional<ProfileModel> updateProfile(UpdateProfileDTO updateProfileDTO) {
        if (updateProfileDTO.getId() == null) return Optional.empty();
        final Optional<ProfileModel> optionalProfile = profileRepository.findById(updateProfileDTO.getId());
        if (!optionalProfile.isPresent()) return Optional.empty();
        else {
            return Optional.of(profileRepository.save(updateProfileDTO.toModel()));
        }
    }

    public Optional<ProfileModel> findProfileById(Long profileId) {
        return profileRepository.findById(profileId);
    }

}
