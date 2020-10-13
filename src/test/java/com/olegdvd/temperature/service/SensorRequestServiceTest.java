package com.olegdvd.temperature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olegdvd.temperature.domain.GatheredSensorData;
import com.olegdvd.temperature.domain.SensorData;
import com.olegdvd.temperature.domain.SensorInfo;
import com.olegdvd.temperature.repository.GatheredSensorDateRepository;
import com.olegdvd.temperature.repository.SensorInfoLocalRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorRequestServiceTest {

    private static final String URL_STRING = "http://test.com/value";

    @Mock
    private Clock clock;
    @Mock
    private SensorInfoLocalRepository repository;
    @Mock
    private GatheredSensorDateRepository gatheredrepository;


    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    private SensorRequestService serviceUnderTest;


    private String json;

    @Before
    public void setUp() throws Exception {
        json = Files.lines(Paths.get(new ClassPathResource("response.json").getURI()))
                .collect(Collectors.joining());

        when(clock.millis()).thenReturn(1601402400000L);
        Instant instant = Instant.ofEpochMilli(1601402400000L);
        when(clock.instant()).thenReturn(instant);
        when(clock.getZone()).thenReturn(ZoneId.of("UTC"));
        when(clock.getZone().getRules().getOffset(instant)).thenReturn(ZoneOffset.UTC);

        List<GatheredSensorData> list = Arrays.asList(
                new GatheredSensorData(new SensorInfo("test1", URL_STRING), clock)
        );


        serviceUnderTest = new SensorRequestService(restTemplate, repository, gatheredrepository, clock);

        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void should_get_DataFromSensor() throws URISyntaxException, JsonProcessingException {
        SensorInfo info = new SensorInfo("test1", URL_STRING);
        SensorData sensorData = null;
        try {
            sensorData = mapper.readValue(json, SensorData.class);
            System.out.println(sensorData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert sensorData != null;
        GatheredSensorData gatheredSensorData = new GatheredSensorData(sensorData, info, clock);
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(URL_STRING)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(gatheredSensorData))
                );

        GatheredSensorData gsData = serviceUnderTest.getDataFromSensor(info);
        System.out.println(gsData);
        assertNotNull(gsData);
        mockServer.verify();
        Assert.assertEquals(gatheredSensorData, gsData);
    }
}