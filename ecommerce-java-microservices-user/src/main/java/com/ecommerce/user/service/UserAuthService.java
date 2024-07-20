package com.ecommerce.user.service;

import com.ecommerce.user.entity.UserEntity;
import com.ecommerce.user.exceptions.ApplicationException;
import com.ecommerce.user.model.request.UserCreateRequest;
import com.ecommerce.user.model.request.UserSignInRequest;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public UserEntity createUser(UserCreateRequest createRequest) throws ApplicationException {
        if(userRepository.findByEmail(createRequest.getEmail()).isPresent())
            throw new ApplicationException("User already exists", 001);
        //TODO use mapper library for mapping entities
        UserEntity userEntity = UserEntity.builder()
                .firstName(createRequest.getFirstName())
                .lastName(createRequest.getLastName())
                .email(createRequest.getEmail())
                .password(encoder.encode(createRequest.getPassword())).build();
        return userRepository.save(userEntity);
    }

    public UserEntity signIn(UserSignInRequest userSignInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSignInRequest.getUsername(), userSignInRequest.getPassword()));
        return userRepository.findByEmail(userSignInRequest.getUsername()).orElseThrow();

    }
}
