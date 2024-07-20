package com.ecommerce.user.model.request;

import lombok.Data;

@Data
public class UserSignInRequest {

    private String username;

    private String password;
}
