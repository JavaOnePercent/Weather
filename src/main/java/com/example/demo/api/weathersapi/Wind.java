package com.example.demo.api.weathersapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {
    private float speed;
    private float deg;

    public float getSpeed(){
        return speed;
    }
    public void setSpeed(float speed){
        this.speed = speed;
    }
    public float getDeg(){
        return deg;
    }
    public void setDeg(float deg){
        this.deg = deg;
    }
}
