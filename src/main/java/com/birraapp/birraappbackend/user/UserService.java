package com.birraapp.birraappbackend.user;

import com.birraapp.birraappbackend.user.model.dto.CreateUserDTO;
import com.birraapp.birraappbackend.user.model.dto.UpdateUserDTO;
import com.birraapp.birraappbackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(CreateUserDTO createUserDto) {
        return userRepository.save(createUserDto.toModel());
    }

    public UserModel updateUser(UpdateUserDTO updateUserDTO) {
        return userRepository.save(updateUserDTO.toModel());
    }

    public Optional<UserModel> getUserById(String userId) {
        return userRepository.findById(userId);
    }

}
