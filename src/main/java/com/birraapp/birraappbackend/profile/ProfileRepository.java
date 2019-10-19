package com.birraapp.birraappbackend.profile;

import com.birraapp.birraappbackend.profile.model.ProfileModel;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileModel, Long> {
}
