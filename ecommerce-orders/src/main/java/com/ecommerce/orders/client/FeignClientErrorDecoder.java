package com.ecommerce.orders.client;

import com.ecommerce.orders.exceptions.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class FeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorDecoder errorDecoder = new Default();
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            ExceptionMessage exceptionMessage = mapper.readValue(bodyIs, ExceptionMessage.class);
            switch (response.status()) {
                case 400:
                    return new ApplicationException(exceptionMessage.getDetail(), 001);
                default:
                    return errorDecoder.decode(methodKey, response);
            }
        } catch (IOException e) {
            return new ApplicationException("Failed to parse the response - " + e.getMessage(), 500);
        }

    }
}

