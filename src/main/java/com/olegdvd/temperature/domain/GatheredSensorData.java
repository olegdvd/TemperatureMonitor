package com.olegdvd.temperature.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class GatheredSensorData {

    @Transient
    private final Clock clock;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sensorId;
    private double value1;
    private double value2;
    private double value3;
    private double value4;
    private LocalDateTime timestamp;

    public GatheredSensorData() {
        this(null);
    }

    public GatheredSensorData(Clock clock) {
        this.clock = clock;
    }

    @Autowired
    public GatheredSensorData(SensorData sensorData, SensorInfo sensorInfo, Clock clock) {
        this.sensorId = sensorInfo.getId();
        this.value1 = sensorData.getValue1();
        this.value2 = sensorData.getValue2();
        this.value3 = sensorData.getValue3();
        this.value4 = sensorData.getValue4();
        this.clock = clock;
        timestamp = LocalDateTime.now(this.clock);
    }

    public GatheredSensorData(SensorInfo sensorInfo, Clock clock) {
        this.sensorId = sensorInfo.getId();
        this.value1 = 0;
        this.value2 = 0;
        this.value3 = 0;
        this.value4 = 0;
        this.clock = clock;
        timestamp = LocalDateTime.now(this.clock);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSensorId(String id) {
        this.sensorId = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getValue3() {
        return value3;
    }

    public void setValue3(double value3) {
        this.value3 = value3;
    }

    public double getValue4() {
        return value4;
    }

    public void setValue4(double value4) {
        this.value4 = value4;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("sensorId", sensorId)
                .append("value1", value1)
                .append("value2", value2)
                .append("value3", value3)
                .append("value4", value4)
                .append("timestamp", timestamp)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatheredSensorData that = (GatheredSensorData) o;
        return sensorId.equals(that.sensorId) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, timestamp);
    }
}
