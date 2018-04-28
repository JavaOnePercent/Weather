package com.example.demo.repository;

import com.example.demo.models.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository  extends JpaRepository<CurrencyModel, Long> {
    CurrencyModel findByIdAndEuroBetweenAndDollarBetween(Long id, Float euro1, Float euro2, Float dollar1, Float dollar2);
    CurrencyModel findFirstByOrderByIdDesc();
    List<CurrencyModel> findByOrderByDayDescTimeDesc();

}
