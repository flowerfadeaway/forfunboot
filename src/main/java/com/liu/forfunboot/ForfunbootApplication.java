package com.liu.forfunboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
public class ForfunbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForfunbootApplication.class, args);
    }

}
