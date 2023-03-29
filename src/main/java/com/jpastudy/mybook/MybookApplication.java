package com.jpastudy.mybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MybookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybookApplication.class, args);
    }

}
