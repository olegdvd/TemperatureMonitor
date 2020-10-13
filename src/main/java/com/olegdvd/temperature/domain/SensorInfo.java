package com.olegdvd.temperature.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SensorInfo {

    private String id;
    private String url;

    public SensorInfo(String id, String url) {
        this.id = id;
        this.url = url;
    }

    @Id
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
