package com.example.thc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TeaHouseChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeaHouseChatServerApplication.class, args);
    }

}
