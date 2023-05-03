package com.example.cloneboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.cloneboard", "com.example.config"})
public class CloneBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloneBoardApplication.class, args);
    }
}