package io.pivotal.pal.tracker;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PalTrackerApplicationTest {

    @Test
    public void objectMapper() throws IOException {
        Assertions.assertThat(new PalTrackerApplication().objectMapper().readValue("\"2018-10-22\"", LocalDate.class)).isEqualTo(LocalDate.parse("2018-10-22"));
    }
}