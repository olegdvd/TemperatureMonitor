package com.olegdvd.temperature.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GatheredSensorDataTest {

    private List<GatheredSensorData> list;
    private Clock woodenClock = Clock.systemUTC();
    @Before
    public void setUp() {
        list = Arrays.asList(
                new GatheredSensorData(new SensorInfo("test1", "url1"), woodenClock),
                new GatheredSensorData(new SensorInfo("test1", "url1"), woodenClock),
                new GatheredSensorData(new SensorInfo("test1", "url1"), woodenClock)
        );

    }

    @Test
    public void should_save_one_in_set() {
        Set<GatheredSensorData> set = new HashSet<>(list);

        assertEquals(1, set.size());
    }

    @Test
    public void timestamp_of_created_is_different() throws InterruptedException {
        Set<GatheredSensorData> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(20); //adding latency between adding
            set.add(new GatheredSensorData(new SensorInfo("test1", "url1"), woodenClock));
        }

        assertEquals(5, set.size());
    }
}