package org.example.resttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ResttestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResttestApplication.class, args);
    }

}
