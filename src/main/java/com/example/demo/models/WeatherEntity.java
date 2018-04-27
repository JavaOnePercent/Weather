package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "weather", schema = "sbertech_db", catalog = "")
public class WeatherEntity extends Model {

    @Column(name = "city")
    private String city;

    @Column(name = "temperature")
    private Integer temperature;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

}
