package com.example.demo.tablemodels;

import com.example.demo.models.CurrencyModel;

import java.util.List;

public class CurrencyTableModel extends TableModel {

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Дата";
            case 1: return "Время";
            case 2: return "Курс доллара \u20BD";
            case 3: return "Курс евро \u20BD";
        }
        return "";
    }

    public void addDate(List<CurrencyModel> currencyEntities) {
        for(int i = 0; i < currencyEntities.size(); i++) {
            String []row = { currencyEntities.get(i).getDay().toString(),
                    currencyEntities.get(i).getTime().toString(),
                    currencyEntities.get(i).getDollar().toString(),
                    currencyEntities.get(i).getEuro().toString() };
            addDate(row);
        }
    }
}