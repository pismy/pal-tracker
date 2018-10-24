package io.pivotal.pal.tracker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeEntryRepository extends CrudRepository<TimeEntry, Long> {
//    TimeEntry create(TimeEntry timeEntry);
//    TimeEntry find(Long id);
//    List<TimeEntry> list();
//    TimeEntry update(long id, TimeEntry timeEntry);
//    void delete(long id);
}
