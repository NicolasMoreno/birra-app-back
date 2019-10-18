package com.birraapp.birraappbackend.oauthConfig;

//import com.birraapp.birraappbackend.user.model.UserModel;
//import com.birraapp.birraappbackend.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

//@Service
//@Transactional implements UserDetailsService
public class UserDetailServiceImpl {

//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        final Optional<UserModel> possibleUser = userRepository.findByUsername(s);
//        if(!possibleUser.isPresent()) throw new UsernameNotFoundException(s);
//        return User.builder()
//                .username(possibleUser.get().getUsername())
//                .password(possibleUser.get().getPassword())
//                .authorities(Collections.emptyList())
//                .build();
//    }
}
