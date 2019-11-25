package com.sdm.StarRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {"com.sdm.StarRental"})
@EnableScheduling
public class StarRentalApplication {

    public static void main(String[] args) {


        SpringApplication.run(StarRentalApplication.class, args);
    }
    
  
}

