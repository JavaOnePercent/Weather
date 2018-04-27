package com.example.demo.currenciesapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotes {
    private float usdeur;
    private float usdrub;

    public float getUsdeur(){
        return usdeur;
    }
    public void setUsdeur(float usdeur){
        this.usdeur = usdeur;
    }
    public float getUsdrub(){
        return usdrub;
    }
    public void setUsdrub(float usdrub){
        this.usdrub = usdrub;
    }
}
