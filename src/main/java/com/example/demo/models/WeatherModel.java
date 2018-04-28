package com.example.demo.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
//@Table(name = "weather", schema = "sbertech_db", catalog = "")
public class WeatherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date day;

    private Time time;

    private String city;

    private Integer temperature;

    public WeatherModel() {}

    public WeatherModel(Date day, Time time, String city, Integer temperature) {
        this.day = day;
        this.time = time;
        this.city = city;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

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
