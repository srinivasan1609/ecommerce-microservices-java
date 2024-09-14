package com.ecommerce.orders.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final int errorCode;

    public ApplicationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }



}
