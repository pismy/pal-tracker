package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final Map<Long, TimeEntry> id2TimeEntry = new HashMap<>();
    private int cursor;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++cursor);
        id2TimeEntry.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return id2TimeEntry.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(id2TimeEntry.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(find(id) == null) {
            // FIXME ???
            return null;
        }
        timeEntry.setId(id);
        id2TimeEntry.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(long id) {
        id2TimeEntry.remove(id);
    }
}
