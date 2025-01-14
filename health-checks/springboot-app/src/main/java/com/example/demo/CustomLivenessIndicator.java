package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customLiveness")
public class CustomLivenessIndicator implements HealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(CustomLivenessIndicator.class);

    private Integer NO_FAILS = 3;

    @Override
    public Health health() {
        boolean live = checkLiveness();
        logger.info("Live: {}", live);
        return live ? Health.up().build() : Health.down().withDetail("Reason", "Liveness check failed").build();
    }

    private boolean checkLiveness() {
        if(NO_FAILS > 0) // simulate that after 3 hits on /liveness endpoint, will return true
            NO_FAILS--;

        return NO_FAILS.equals(0);
    }
}
