package com.example.demo.weathersAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weathers {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;

    public List<Weather> getWeather(){
        return weather;
    }
    public void setWeather(List<Weather> weather){
        this.weather = weather;
    }
    public Main getMain(){
        return main;
    }
    public void setMain(Main main){
        this.main = main;
    }
    public Wind getWind(){
        return wind;
    }
    public void setWind(Wind wind){
        this.wind = wind;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
