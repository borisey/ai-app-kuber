package com.example.spring_boot_monitoring_demo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SentimentController {

    private final Counter requestsCounter;

    public SentimentController(MeterRegistry registry) {
        this.requestsCounter = Counter.builder("sentiment_requests_total")
                .description("Общее количество запросов на анализ тональности текста")
                .register(registry);
    }

    @GetMapping("/api/sentiment")
    public Map<String, String> getSentiment(@RequestParam String text) {
        requestsCounter.increment();
        String sentiment = analyzeSentiment(text);
        return Map.of("sentiment", sentiment);
    }

    private String analyzeSentiment(String text) {
        String lower = text.toLowerCase();

        if (lower.contains("hello") || lower.contains("good") || lower.contains("happy")) {
            return "positive";
        } else if (lower.contains("bad") || lower.contains("sad") || lower.contains("angry")) {
            return "negative";
        } else {
            return "neutral";
        }
    }
}