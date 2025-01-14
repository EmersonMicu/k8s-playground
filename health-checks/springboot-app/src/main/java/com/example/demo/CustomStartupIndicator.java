package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customStartup")
public class CustomStartupIndicator implements HealthIndicator {
    private static final Logger logger = LoggerFactory.getLogger(CustomStartupIndicator.class);
    private Integer NO_FAILS = 3;

    @Override
    public Health health() {
        boolean started = checkIsStarted();
        logger.info("Started: {}", started);
        return started ? Health.up().build() : Health.down().withDetail("status", "Application is starting").build();
    }

    private boolean checkIsStarted() {
        if(NO_FAILS > 0) // simulate that after 3 hits on /startup endpoint, will return true
            NO_FAILS--;

        return NO_FAILS.equals(0);
    }
}