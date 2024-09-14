package com.ecommerce.user.model.response;

import lombok.Data;

@Data
public class SignInResponse {

    private String accessToken;
    private String refreshToken;
}
