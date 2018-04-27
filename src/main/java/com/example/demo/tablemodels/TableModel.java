package com.example.demo.tablemodels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public abstract class TableModel extends AbstractTableModel {

    private int columnCount = 4;
    private ArrayList<String []> dataArrayList;

    public TableModel() {
        dataArrayList = new ArrayList<String []>();
        for(int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[columnCount]);
        }
    }

    //возвращает количество строк в таблице
    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    //возвращает количество колонок в таблице
    @Override
    public int getColumnCount() {
        return columnCount;
    }

    //получает значение с определённой ячейки таблицы по номеру строки и столбца
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String []rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    //добавление данных в таблицу
    public void addDate(String []row) {
        String []rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }
}
