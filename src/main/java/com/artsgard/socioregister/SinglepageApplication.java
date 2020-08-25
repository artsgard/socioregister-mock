package com.artsgard.socioregister;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author WillemDragstra
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.artsgard.socioregister.*"})
public class SinglepageApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SinglepageApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    }
}

/*
{
        "id": 4,
        "username": "cm",
        "firstName": "Claudio",
        "lastName": "Monteverdi",
        "email": "madrigal@gmail.com",
        "active": true 
}
*/