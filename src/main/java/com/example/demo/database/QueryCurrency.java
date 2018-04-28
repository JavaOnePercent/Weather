package com.example.demo.database;

import com.example.demo.models.CurrencyModel;
import com.example.demo.models.WeatherModel;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class QueryCurrency {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public QueryCurrency(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public CurrencyModel getLastWeather() {
        return currencyRepository.findFirstByOrderByIdDesc();
    }

    public List<CurrencyModel> getAllCurrency() {
        return currencyRepository.findByOrderByDayDescTimeDesc();
    }

    public CurrencyModel checkIdentity(Float euro1, Float euro2, Float dollar1, Float dollar2) {
        return currencyRepository.findByIdAndEuroBetweenAndDollarBetween(currencyRepository.count(), euro1, euro2, dollar1, dollar2);
    }

    public void saveCurrency(Float euro, Float dollar) {
        currencyRepository.saveAndFlush(new CurrencyModel( new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), euro, dollar));
    }
}
