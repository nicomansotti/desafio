package com.datosasistente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default() {
            @Override
            public Exception decode(String methodKey, Response response) {
                if (response.status() >= 400 && response.status() < 500) {
                    return new RuntimeException("Error de cliente: " + response.status());
                } else if (response.status() >= 500) {
                    return new RuntimeException("Error de servidor: " + response.status());
                }
                return super.decode(methodKey, response);
            }
        };
    }
}