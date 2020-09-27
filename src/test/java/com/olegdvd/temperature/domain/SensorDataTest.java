package com.olegdvd.temperature.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SensorDataTest {

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();
    private String json;

    @Before
    public void setUp() throws Exception {
        json = Files.lines(Paths.get(new ClassPathResource("response.json").getURI()))
                .collect(Collectors.joining());
    }

    @Test
    public void should_map_json_to_SensorData(){
        SensorData sensorData = null;
        try {
            sensorData = mapper.readValue(json, SensorData.class);
            System.out.println(sensorData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert sensorData != null;
        assertEquals(5494.518, sensorData.getValue1(), 0);
    }
}