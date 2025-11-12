package com.example.spring_boot_monitoring_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot app!";
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @GetMapping("/cpu-load")
    public String burnCpu(@RequestParam(defaultValue = "10") int seconds) {
        long end = System.currentTimeMillis() + seconds * 1000L;
        while (System.currentTimeMillis() < end) {
            Math.sqrt(Math.random());
        }
        return "CPU load for " + seconds + "s";
    }
}