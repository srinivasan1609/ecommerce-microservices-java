package com.ecommerce.orders.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionMessage {
    private String title;
    private int status;
    private String detail;
    private String description;
    private String instance;
    private String type;
    private String load;
    // standard getters and setters
}
