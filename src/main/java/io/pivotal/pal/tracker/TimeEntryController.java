package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository repo) {
        this.repo = repo;
    }

    @ResponseBody
    public ResponseEntity<TimeEntry> create(TimeEntry timeEntry) {
        return new ResponseEntity<>(repo.create(timeEntry), HttpStatus.CREATED);
    }

    @ResponseBody
    public ResponseEntity<TimeEntry> update(long id, TimeEntry timeEntry) {
        TimeEntry updated = repo.update(id, timeEntry);
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @ResponseBody
    public ResponseEntity<TimeEntry> read(long id) {
        TimeEntry timeEntry = repo.find(id);
        if(timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @ResponseBody
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(repo.list(), HttpStatus.OK);
    }

    @ResponseBody
    public ResponseEntity<TimeEntry> delete(long id) {
        repo.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
