package com.example.demo.forms;

import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.*;

@Component
public class Settings extends JFrame {
    private JPanel cityPanel;
    private JTextField textFieldCity;
    private JButton okButton;
    private JLabel labelTitle;
    private JButton backButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    private final WeatherRepository weatherRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public Settings(WeatherRepository weatherRepository, CurrencyRepository currencyRepository) {
        super("Настройки");
        this.setSize(540, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setContentPane(cityPanel);
        this.weatherRepository = weatherRepository;
        this.currencyRepository = currencyRepository;
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onMainFormWithCity();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onMainFormWithoutCity();
            }
        });
    }

    private void onMainFormWithCity() {
        cityPanel.setVisible(false);
        MainForm mainForm = new MainForm(weatherRepository, currencyRepository);
        mainForm.setCity(textFieldCity.getText());
        mainForm.setVisible(true);
        dispose();
    }

    private void onMainFormWithoutCity() {
        cityPanel.setVisible(false);
        MainForm mainForm = new MainForm(weatherRepository, currencyRepository);
        mainForm.setVisible(true);
        dispose();
    }
}
