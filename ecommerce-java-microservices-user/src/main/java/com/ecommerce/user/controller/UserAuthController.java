package com.ecommerce.user.controller;

import com.ecommerce.user.entity.UserEntity;
import com.ecommerce.user.exceptions.ApplicationException;
import com.ecommerce.user.model.request.UserCreateRequest;
import com.ecommerce.user.model.request.UserSignInRequest;
import com.ecommerce.user.security.JwtService;
import com.ecommerce.user.service.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody UserCreateRequest createRequest) throws ApplicationException {
        return ResponseEntity.ok(userAuthService.createUser(createRequest));
    }

    @Operation(summary = "Login")
    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody UserSignInRequest signInRequest) {
        UserEntity user = userAuthService.signIn(signInRequest);
        return ResponseEntity.ok(jwtService.generateToken(user));
    }

}
