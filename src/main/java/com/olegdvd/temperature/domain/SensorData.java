package com.olegdvd.temperature.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SensorData {

    private String id;
    private double value1;
    private double value2;
    private double value3;
    private double value4;
    private LocalDateTime timestamp;

    public SensorData(double value1, double value2, double value3, double value4, LocalDateTime timestamp) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorData that = (SensorData) o;
        return id.equals(that.id) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp);
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("value1", value1)
                .append("value2", value2)
                .append("value3", value3)
                .append("value4", value4)
                .append("timestamp", timestamp)
                .toString();
    }
}
