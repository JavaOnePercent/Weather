package com.example.demo.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
//@Table(name = "currency", schema = "sbertech_db", catalog = "")
public class CurrencyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date day;

    private Time time;

    private Float euro;

    private Float dollar;

    public CurrencyModel() {}

    public CurrencyModel(Date day, Time time, Float euro, Float dollar) {
        this.day = day;
        this.time = time;
        this.euro = euro;
        this.dollar = dollar;
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

    public Float getEuro() {
        return euro;
    }

    public void setEuro(Float euro) {
        this.euro = euro;
    }

    public Float getDollar() {
        return dollar;
    }

    public void setDollar(Float dollar) {
        this.dollar = dollar;
    }

}
