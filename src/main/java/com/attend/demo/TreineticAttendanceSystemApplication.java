package com.attend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class TreineticAttendanceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreineticAttendanceSystemApplication.class, args);
    }
}
