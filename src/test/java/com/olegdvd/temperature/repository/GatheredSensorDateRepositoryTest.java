package com.olegdvd.temperature.repository;

import com.olegdvd.temperature.domain.GatheredSensorData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GatheredSensorDateRepositoryTest {

    private static final String SENSOR_1 = "sensor1";
    private static final String SENSOR_2 = "sensor2";
    private static final String SENSOR_3 = "sensor3";

    @Mock
    private Clock clock;
    @Autowired
    private GatheredSensorDateRepository repository;

    @Autowired
    private TestEntityManager em;

    @Before
    public void setUp() {
        when(clock.millis()).thenReturn(1601402400000L, 1601402400001L, 1601402400002L, 1601402400003L);
        when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
//        when(clock.getZone().getRules().getOffset(any(Instant.class))).thenReturn(ZoneOffset.UTC);
        when(clock.instant()).thenReturn(
                Instant.ofEpochMilli(1601402400000L), Instant.ofEpochMilli(1601402400001L),
                Instant.ofEpochMilli(1601402400002L), Instant.ofEpochMilli(1601402400003L));
        List<GatheredSensorData> list = Arrays.asList(
                createGatheredSensorData(1L, SENSOR_1, 123.01, 234.01, 456.01),
                createGatheredSensorData(2L, SENSOR_2, 987.01, 876.01, 654.00),
                createGatheredSensorData(3L, SENSOR_3, 654.01, 543.01, 432.01),
                createGatheredSensorData(4L, SENSOR_1, 012.01, 123.01, 345.01)
        );
        list.forEach(em::merge);
        em.flush();
    }

    private GatheredSensorData createGatheredSensorData(long id, String sensorId, double s1, double s2, double s3) {
        GatheredSensorData gatheredSensorData = new GatheredSensorData(clock);
        gatheredSensorData.setId(id);
        gatheredSensorData.setSensorId(sensorId);
        gatheredSensorData.setValue1(s1);
        gatheredSensorData.setValue1(s2);
        gatheredSensorData.setValue1(s3);
        gatheredSensorData.setTimestamp(LocalDateTime.now(clock));
        return gatheredSensorData;
    }

    @Test
    public void should_find_All_data_of_SensorId() {
        List<GatheredSensorData> allBySensorId = repository.findAllBySensorId(SENSOR_1);
        allBySensorId.forEach(System.out::println);

        assertEquals(2, allBySensorId.size());
    }
}