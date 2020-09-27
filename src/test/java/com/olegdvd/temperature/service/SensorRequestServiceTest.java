package com.olegdvd.temperature.service;

import com.olegdvd.temperature.domain.GatheredSensorData;
import com.olegdvd.temperature.domain.SensorData;
import com.olegdvd.temperature.domain.SensorInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorRequestServiceTest {

//    private static final String URL_STRING = "http://192.168.0.77/value";

//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    private SensorRequestService service;

    @Test
    public void getDataFromSensor() {
        SensorInfo info = new SensorInfo("1", "http://192.168.0.77/value");
        GatheredSensorData sensorData = service.getDataFromSensor(info);
        System.out.println(sensorData);

        assertNotNull(sensorData);
    }

    @Test
    public void should_process_sensors_from_list() {
        int items = service.processSensors();

        assertEquals(1, items);
    }
}