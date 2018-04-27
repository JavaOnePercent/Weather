package com.example.demo.tablemodels;

import com.example.demo.models.WeatherEntity;

import java.util.List;

public class WeatherTableModel extends TableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Дата";
            case 1: return "Время";
            case 2: return "Город";
            case 3: return "Температура °C";
        }
        return "";
    }

    public void addDate(List<WeatherEntity> weatherEntity) {
        for(int i = 0; i < weatherEntity.size(); i++) {
            String []row = { weatherEntity.get(i).getDay().toString(),
                    weatherEntity.get(i).getTime().toString(),
                    weatherEntity.get(i).getCity(),
                    weatherEntity.get(i).getTemperature().toString() };
            addDate(row);
        }
    }
}
