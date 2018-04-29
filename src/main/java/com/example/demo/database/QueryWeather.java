package com.example.demo.database;

import com.example.demo.models.WeatherModel;
import com.example.demo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class QueryWeather {

    private final WeatherRepository weatherRepository;

    @Autowired
    public QueryWeather(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public String getCity() {
        return weatherRepository.findFirstByOrderByIdDesc().getCity();
    }

    public WeatherModel getLastWeather() {
        return weatherRepository.findFirstByOrderByIdDesc();
    }

    public List<WeatherModel> getAllWeather() {
        return weatherRepository.findByOrderByDayDescTimeDesc();
    }

    public WeatherModel checkIdentity(String city, Integer temperature) {
        return weatherRepository.findByIdAndCityAndTemperature(weatherRepository.count(), city, temperature);
    }

    public void saveWeather(String city, Integer temperature) {
        weatherRepository.saveAndFlush(new WeatherModel( new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), city, temperature));
    }


    
    

}
