package com.example.demo.weathersAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private int temp;
    private float humidity;
    private float pressure;

    public int getTemp(){
        return temp;
    }
    public void setTemp(int temp){
        this.temp = temp;
    }
    public float getHumidity(){
        return humidity;
    }
    public void setHumidity(float humidity){
        this.humidity = humidity;
    }
    public float getPressure(){
        return pressure;
    }
    public void setPressure(float pressure){
        this.pressure = pressure;
    }
}
