package com.example.demo.tablemodels;

import com.example.demo.models.WeatherModel;

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

    public void addDate(List<WeatherModel> weatherModel) {
        for(int i = 0; i < weatherModel.size(); i++) {
            String []row = { weatherModel.get(i).getDay().toString(),
                    weatherModel.get(i).getTime().toString(),
                    weatherModel.get(i).getCity(),
                    weatherModel.get(i).getTemperature().toString() };
            addDate(row);
        }
    }
}
