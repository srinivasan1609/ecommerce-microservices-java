package com.ecommerce.user.controller;

import com.ecommerce.user.entity.UserEntity;
import com.ecommerce.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("users/v1")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserProfileService userDetailsService;

    @GetMapping(value = "/details")
    public ResponseEntity<UserEntity> getDetails(Authentication authentication) {
        return ResponseEntity.ok(userDetailsService.getUserDetails(authentication.getName()));
    }


}
