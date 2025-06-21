package com.arvind.smartcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartControlApplication {

    public static void main(String[] args) {
        // 👇 Disable headless mode
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(SmartControlApplication.class, args);
    }
}
