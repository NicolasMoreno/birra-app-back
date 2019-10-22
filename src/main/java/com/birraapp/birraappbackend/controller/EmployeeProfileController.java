package com.birraapp.birraappbackend.controller;

import com.birraapp.birraappbackend.profile.ProfileService;
import com.birraapp.birraappbackend.profile.model.ProfileModel;
import com.birraapp.birraappbackend.profile.model.dto.CreateProfileDTO;
import com.birraapp.birraappbackend.profile.model.dto.UpdateProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee-profile")
public class EmployeeProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/")
    public ResponseEntity addProfile(@RequestBody CreateProfileDTO createProfileDTO) {
        return ResponseEntity.ok(profileService.saveProfile(createProfileDTO));
    }

    @PutMapping("/")
    public ResponseEntity updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO) {
        final Optional<ProfileModel> optionalProfileModel = profileService.updateProfile(updateProfileDTO);
        if (optionalProfileModel.isPresent()) return ResponseEntity.ok(optionalProfileModel.get());
        else return ResponseEntity.status(400).body("Perfil a editar no encontrado");
    }

    @GetMapping("/{id}")
    public ResponseEntity findProfileById(@PathVariable Long id) {
        final Optional<ProfileModel> profileById = profileService.findProfileById(id);
        if (profileById.isPresent()) return ResponseEntity.ok(profileById.get());
        else return ResponseEntity.status(400).body("No se encontró el perfil");
    }
}
