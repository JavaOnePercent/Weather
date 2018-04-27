package com.example.demo.forms;

import com.example.demo.SberTech;
import com.example.demo.currenciesapi.Currencies;
import com.example.demo.models.CurrencyEntity;
import com.example.demo.models.WeatherEntity;
import com.example.demo.weathersAPI.Weathers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel buttonPanel;
    private JPanel topPanel;
    private JPanel currencyPanel;
    private JPanel weatherPanel;
    private JButton buttonHistory;
    private JButton buttonSettings;
    private JLabel labelDollar;
    private JLabel labelEuro;
    private JLabel labelCourseEuro;
    private JLabel labelCourseDollar;
    private JLabel labelTitleCourse;
    private JLabel labelCity;
    private JLabel labelDegree;
    private JLabel labelTitleWeather;
    private JLabel labelIcon;
    private JLabel labelDescription;
    private JLabel labelPressure;
    private JLabel labelHumidity;
    private JLabel labelSpeed;
    private JLabel labelDeg;
    private JLabel checkCurrency;
    private JLabel checkWeather;
    private String city = null;

    public MainForm() {
        super("SberTech");
        this.setSize(540, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setContentPane(mainPanel);
        SberTech sberTech = new SberTech();
        ActionListener listenerWeather = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    checkWeather.setVisible(false);

                    setCity(city);
                    if (city == null)
                        city = sberTech.TransactGetCity();
                    if (city == null)
                        city = "Yekaterinburg";
                    Weathers weathers = null;
                    String inputJson = sberTech.json("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=168f02697ac2e075a37c762c9e2dd423&lang=ru&units=metric");
                    System.out.println(inputJson);
                    if (inputJson != null) {
                        weathers = sberTech.APIWeather(inputJson);
                        BufferedImage myPicture = ImageIO.read(new URL("http://openweathermap.org/img/w/" + weathers.getWeather().get(0).getIcon() + ".png"));
                        sberTech.TransactSaveWeather(weathers);
                        labelIcon.setIcon(new ImageIcon(myPicture));
                        labelDescription.setText(weathers.getWeather().get(0).getDescription());
                        labelPressure.setText("Давление: " + sberTech.Format(sberTech.Pressure(weathers.getMain().getPressure()), "#.##") + " mmHg");
                        labelHumidity.setText("Влажность: " + sberTech.Format(weathers.getMain().getHumidity(), "#.#")  + " %");
                        labelSpeed.setText("Скорость ветра: " + weathers.getWind().getSpeed() + " m/s");
                        labelDeg.setText("Направление ветра: " + sberTech.Degree(weathers.getWind().getDeg()));
                        checkWeather.setVisible(true);
                        checkWeather.setToolTipText(sberTech.Update());
                    }
                    WeatherEntity weatherEntity = sberTech.TransactWeatherMain();
                    if(weatherEntity != null) {
                        labelCity.setText(weatherEntity.getCity());
                        labelDegree.setText(weatherEntity.getTemperature() + " °C");
                    }
                    else {
                        labelCity.setText("Нет данных");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        ActionListener listenerCurrency = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    checkCurrency.setVisible(false);
                    Currencies currencies = null;
                    String inputJson = sberTech.json("http://www.apilayer.net/api/live?access_key=952fb1b2e8d67623436534f0099e0e6e");
                    System.out.println(inputJson);
                    if (inputJson != null) {
                        currencies = sberTech.APICurrency(inputJson);
                        sberTech.TransactSaveCurrency(currencies);
                        checkCurrency.setVisible(true);
                        checkCurrency.setToolTipText(sberTech.Update());
                    }
                    CurrencyEntity currencyEntity = sberTech.TransactCurrencyMain();
                    if(currencyEntity != null) {
                        labelCourseDollar.setText(currencyEntity.getDollar() + " \u20BD");
                        labelCourseEuro.setText(currencyEntity.getEuro() + " \u20BD");
                    }
                    else {
                        labelCourseDollar.setText("Нет данных");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        Timer timerWeather = new Timer(60000, listenerWeather);
        timerWeather.setInitialDelay(1);
        timerWeather.start();

        Timer timerCurrency = new Timer(60000, listenerCurrency);
        timerCurrency.setInitialDelay(1);
        timerCurrency.start();

        buttonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerWeather.stop();
                timerCurrency.stop();
                onHistory();
            }
        });

        buttonSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerWeather.stop();
                timerCurrency.stop();
                onCity();
            }
        });
    }

    public void setCity(String city) {
        this.city = city;
    }

    private void onCity() {
        // add your code here if necessary
        mainPanel.setVisible(false);
        City city = new City();
        city.setVisible(true);
        this.dispose();
    }

    private void onHistory() {
        // add your code here if necessary
        mainPanel.setVisible(false);
        History history = new History();
        history.setVisible(true);
        this.dispose();
    }
}