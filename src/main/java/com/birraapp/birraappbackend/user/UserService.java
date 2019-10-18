package com.birraapp.birraappbackend.user;

import com.birraapp.birraappbackend.user.dto.CreateUserDTO;
import com.birraapp.birraappbackend.user.dto.UpdateUserDTO;
import com.birraapp.birraappbackend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
