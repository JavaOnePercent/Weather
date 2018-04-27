package com.example.demo.forms;

import com.example.demo.tablemodels.CurrencyTableModel;
import com.example.demo.SberTech;
import com.example.demo.tablemodels.WeatherTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class History extends JFrame{
    private JPanel historyPanel;

    public History() {
        super("История");
        this.setSize(540, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setContentPane(historyPanel);

        JButton buttonBack = new JButton("Назад");

        WeatherTableModel weatherTableModel = new WeatherTableModel();
        JTable tableWeather = new JTable(weatherTableModel);
        tableWeather.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableWeatherScroolPane = new JScrollPane(tableWeather);
        tableWeatherScroolPane.setPreferredSize(new Dimension(540, 160));

        CurrencyTableModel currencyTableModel = new CurrencyTableModel();
        JTable tableCurrency = new JTable(currencyTableModel);
        tableCurrency.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableCurrencyScroolPane = new JScrollPane(tableCurrency);
        tableWeatherScroolPane.setPreferredSize(new Dimension(540, 160));

        historyPanel.setLayout(new GridBagLayout());

        historyPanel.add(tableWeatherScroolPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        historyPanel.add(tableCurrencyScroolPane, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        historyPanel.add(buttonBack, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.CENTER,
                new Insets(1, 1, 1, 1), 0, 0));

        SberTech sberTech = new SberTech();

        weatherTableModel.addDate(sberTech.TransactWeatherTable());
        currencyTableModel.addDate(sberTech.TransactCurrencyTable());

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                historyPanel.setVisible(false);
                MainForm mainForm = new MainForm();
                mainForm.setVisible(true);
                dispose();
            }
        });
    }
}
