package com.example.demo.forms;

import com.example.demo.database.QueryCurrency;
import com.example.demo.database.QueryWeather;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.WeatherRepository;
import com.example.demo.tablemodels.CurrencyTableModel;
import com.example.demo.tablemodels.WeatherTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Component
public class History extends JFrame{
    private JPanel historyPanel;
    private JButton buttonBack;

    private final WeatherRepository weatherRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public History(WeatherRepository weatherRepository, CurrencyRepository currencyRepository) {
        super("История");
        this.setSize(540, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setContentPane(historyPanel);
        this.weatherRepository = weatherRepository;
        this.currencyRepository = currencyRepository;

        QueryWeather queryWeather = new QueryWeather(weatherRepository);
        QueryCurrency queryCurrency = new QueryCurrency(currencyRepository);

        buttonBack = new JButton("Назад");

        WeatherTableModel weatherTableModel = new WeatherTableModel();
        JTable tableWeather = new JTable(weatherTableModel);

        CurrencyTableModel currencyTableModel = new CurrencyTableModel();
        JTable tableCurrency = new JTable(currencyTableModel);

        showTable(tableWeather, tableCurrency);

        weatherTableModel.addDate(queryWeather.getAllWeather());
        currencyTableModel.addDate(queryCurrency.getAllCurrency());

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onMainForm();
            }
        });
    }

    private JScrollPane createTableWeather(JTable tableWeather) {

        tableWeather.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableWeatherScroolPane = new JScrollPane(tableWeather);
        tableWeatherScroolPane.setPreferredSize(new Dimension(540, 160));
        return tableWeatherScroolPane;

    }

    private JScrollPane createTableCurrency(JTable tableCurrency) {

        tableCurrency.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableCurrencyScroolPane = new JScrollPane(tableCurrency);
        tableCurrencyScroolPane.setPreferredSize(new Dimension(540, 160));
        return tableCurrencyScroolPane;
    }

    private void showTable(JTable tableWeather, JTable tableCurrency) {
        historyPanel.setLayout(new GridBagLayout());

        historyPanel.add(createTableWeather(tableWeather), new GridBagConstraints(0, 0, 1, 3, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        historyPanel.add(createTableCurrency(tableCurrency), new GridBagConstraints(0, 3, 1, 3, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        historyPanel.add(buttonBack, new GridBagConstraints(0, 6, 1, 2, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.CENTER,
                new Insets(1, 1, 1, 1), 0, 0));
    }

    private void onMainForm() {
        historyPanel.setVisible(false);
        MainForm mainForm = new MainForm(weatherRepository, currencyRepository);
        mainForm.setVisible(true);
        dispose();
    }
}
