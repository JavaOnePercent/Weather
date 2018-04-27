package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "currency", schema = "sbertech_db", catalog = "")
public class CurrencyEntity extends Model {

    @Column(name = "euro")
    private Float euro;

    @Column(name = "dollar")
    private Float dollar;

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
