package com.ecommerce.products.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends Exception {

    private final int errorCode;

    public ApplicationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }



}
