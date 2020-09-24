package com.olegdvd.temperature.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SensorRequestServiceTest {

    private static final String URL_STRING = "http://192.168.0.77/value";

    @Autowired
    private SensorRequestService service;

    @Test
    public void getDataFromSensor() {
        service.getDataFromSensor(URL_STRING, URL_STRING);
    }
}