package com.ecommerce.orders;

import com.ecommerce.orders.exceptions.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import(GlobalExceptionHandler.class)
public class EcommerceOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrdersApplication.class, args);
	}

}
