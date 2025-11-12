package com.example.spring_boot_monitoring_demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class Metrics {
    private final MeterRegistry registry;

    public Metrics(MeterRegistry registry) {
        this.registry = registry;
    }

    public void incrementRequest() {
        registry.counter("sentiment_requests_total").increment();
    }
}