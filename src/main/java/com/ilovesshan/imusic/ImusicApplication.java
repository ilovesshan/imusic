package com.ilovesshan.imusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImusicApplication.class, args);
    }

}
