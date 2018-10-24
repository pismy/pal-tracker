package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository repo;
    private final CounterService counter;
    private final GaugeService gauge;

    public TimeEntryController(TimeEntryRepository repo, CounterService counter, GaugeService gauge) {
        this.repo = repo;
        this.counter = counter;
        this.gauge = gauge;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
<<<<<<< Updated upstream
        TimeEntry created = repo.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", repo.list().size());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
=======
        return new ResponseEntity<>(repo.save(timeEntry), HttpStatus.CREATED);
>>>>>>> Stashed changes
    }

    @ResponseBody
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long id, @RequestBody TimeEntry toUpdate) {
        Optional<TimeEntry> existing = repo.findById(id);
        if(!existing.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
<<<<<<< Updated upstream

        counter.increment("TimeEntry.updated");
=======
        TimeEntry entity = existing.get();
        entity.setDate(toUpdate.getDate());
        entity.setProjectId(toUpdate.getProjectId());
        entity.setHours(toUpdate.getHours());
        entity.setUserId(toUpdate.getUserId());
        TimeEntry updated = repo.save(entity);
>>>>>>> Stashed changes
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        Optional<TimeEntry> timeEntry = repo.findById(id);
        if(!timeEntry.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
<<<<<<< Updated upstream
        counter.increment("TimeEntry.read");
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
=======
        return new ResponseEntity<>(timeEntry.get(), HttpStatus.OK);
>>>>>>> Stashed changes
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
<<<<<<< Updated upstream
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(repo.list(), HttpStatus.OK);
=======
        return new ResponseEntity<List<TimeEntry>>(StreamSupport.stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList()), HttpStatus.OK);
>>>>>>> Stashed changes
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
<<<<<<< Updated upstream
        repo.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", repo.list().size());
=======
        repo.deleteById(id);
>>>>>>> Stashed changes
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
