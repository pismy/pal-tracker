package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        TimeEntry created = repo.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", repo.list().size());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updated = repo.update(id, timeEntry);
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        counter.increment("TimeEntry.updated");
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry timeEntry = repo.find(id);
        if(timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        counter.increment("TimeEntry.read");
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(repo.list(), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
        repo.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", repo.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
