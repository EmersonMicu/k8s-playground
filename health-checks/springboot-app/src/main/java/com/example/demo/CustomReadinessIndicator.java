package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customReadiness")
public class CustomReadinessIndicator implements HealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(CustomLivenessIndicator.class);

    private static int NO_FAILS = 3;

    @Override
    public Health health() {
        boolean ready = checkReadiness();
        logger.info("Ready: {}", ready);
        return checkReadiness() ? Health.up().build() : Health.down().withDetail("Reason", "Readiness check failed").build();
    }

    private boolean checkReadiness() {
        if(NO_FAILS > 0) // simulate that after 3 hits on /startup endpoint, will return true
            NO_FAILS--;

        return NO_FAILS == 0;
    }
}
