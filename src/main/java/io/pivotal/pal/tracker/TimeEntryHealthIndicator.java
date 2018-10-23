package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
    private final TimeEntryRepository repo;

    public TimeEntryHealthIndicator(TimeEntryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Health health() {
        return repo.list().size() <= 5 ? Health.up().build() : Health.down().build();
    }
}
