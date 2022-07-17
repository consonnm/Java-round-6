package com.example.hotSpot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.hotSpot")
@MapperScan("com.example.hotSpot.dao")
public class HotSpotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotSpotApplication.class, args);
    }

}
