package com.example.projectrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectRestApiApplication.class, args);
    }

}
