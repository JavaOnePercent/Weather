package com.example.demo.repository;

import com.example.demo.models.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherModel, Long>{
    WeatherModel findByIdAndCityAndTemperature(Long id, String city, Integer temperature);
    WeatherModel findFirstByOrderByIdDesc();
    List<WeatherModel> findByOrderByDayDescTimeDesc();
}
