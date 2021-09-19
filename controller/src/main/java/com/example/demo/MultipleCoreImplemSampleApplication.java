package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
        "com.example.demo", "com.example.jpa"})
public class MultipleCoreImplemSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(MultipleCoreImplemSampleApplication.class, args);
  }

}
