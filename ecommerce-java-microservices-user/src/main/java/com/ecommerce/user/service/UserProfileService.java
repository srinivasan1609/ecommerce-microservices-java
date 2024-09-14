package com.ecommerce.user.service;

import com.ecommerce.user.entity.UserEntity;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    UserRepository userRepository;


    public UserEntity getUserDetails(String userName) {
        return userRepository.findByEmail(userName).orElseThrow();
    }


}
