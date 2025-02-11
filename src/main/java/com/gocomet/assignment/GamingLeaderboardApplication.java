package com.gocomet.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GamingLeaderboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingLeaderboardApplication.class, args);
    }

}
