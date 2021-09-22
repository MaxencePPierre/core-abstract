package com.example.demo.repository;

import com.example.demo.dto.in.ShoeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;

@SpringBootApplication
public class AccessShoeJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessShoeJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessShoeJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ShoeRepository repository) {
        return (args) -> {
            // save a few shoes
            repository.save(new ShoeFilter(new BigInteger(String.valueOf(40)), ShoeFilter.Color.BLACK, new BigInteger(String.valueOf(10))));
            repository.save(new ShoeFilter(new BigInteger(String.valueOf(41)), ShoeFilter.Color.BLUE, new BigInteger(String.valueOf(5))));
            repository.save(new ShoeFilter(new BigInteger(String.valueOf(42)), ShoeFilter.Color.BLUE, new BigInteger(String.valueOf(0))));
            repository.save(new ShoeFilter(new BigInteger(String.valueOf(43)), ShoeFilter.Color.BLACK, new BigInteger(String.valueOf(10))));
            repository.save(new ShoeFilter(new BigInteger(String.valueOf(44)), ShoeFilter.Color.BLACK, new BigInteger(String.valueOf(1))));

            // fetch all customers
            log.info("Shoes found with findAll():");
            log.info("-------------------------------");
            for (ShoeFilter shoes : repository.findAll()) {
                log.info(shoes.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            ShoeFilter shoes = repository.findById(1L);
            log.info("Shoes found with findById(1L):");
            log.info("--------------------------------");
            log.info(shoes.toString());
            log.info("");

            // fetch customers by last name
            log.info("Shoes found with findByColorAndSize('BLUE', 40):");
            log.info("--------------------------------------------");
            ShoeFilter blueShoeIn40 = repository.findByColorAndSize(ShoeFilter.Color.BLACK, new BigInteger(String.valueOf(40)));
            log.info(blueShoeIn40.toString());
            log.info("");
            log.info("");
            repository.deleteAll();
            log.info("Database cleared:");
            log.info("--------------------------------------------");
        };
    }
}