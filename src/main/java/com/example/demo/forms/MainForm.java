package com.example.demo.forms;

import com.example.demo.api.Api;
import com.example.demo.database.QueryCurrency;
import com.example.demo.database.QueryWeather;
import com.example.demo.api.currenciesapi.Currencies;
import com.example.demo.models.CurrencyModel;
import com.example.demo.models.WeatherModel;
import com.example.demo.repository.WeatherRepository;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.api.weathersapi.Weathers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Component
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
    private JButton buttonReload;

    private final WeatherRepository weatherRepository;
    private final CurrencyRepository currencyRepository;

    private QueryWeather queryWeather;
    private QueryCurrency queryCurrency;
    private Wrapper wrapper;
    private Api api;
    private String city;

    private Weathers weathers;
    private Currencies currencies;

    @Autowired
    public MainForm(WeatherRepository weatherRepository, CurrencyRepository currencyRepository) {
        super("Wrapper");
        this.setSize(540, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setContentPane(mainPanel);
        this.weatherRepository = weatherRepository;
        this.currencyRepository = currencyRepository;
        wrapper = new Wrapper();
        api = new Api();
        queryWeather = new QueryWeather(weatherRepository);
        queryCurrency = new QueryCurrency(currencyRepository);
        ActionListener listenerWeather = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    checkWeather.setVisible(false);
                    getCity();
                    String inputJson = api.getJson(api.UrlApiWeather(city));
                    if (inputJson != null) {
                        weathers = api.APIWeather(inputJson);
                        WeatherModel weatherModel = queryWeather.checkIdentity(weathers.getName(), weathers.getMain().getTemp());
                        if (weatherModel == null)
                            queryWeather.saveWeather(weathers.getName(), weathers.getMain().getTemp());
                        showOtherDataWeather(getIcon());
                    }
                    showBaseDataWeather(queryWeather.getLastWeather());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        ActionListener listenerCurrency = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    checkCurrency.setVisible(false);
                    String inputJson = api.getJson(api.UrlApiCurrency());
                    if (inputJson != null) {
                        currencies = api.APICurrency(inputJson);
                        CurrencyModel currencyModel = queryCurrency.checkIdentity(wrapper.Before(wrapper.getEurrub(currencies.getQuotes().getUsdrub(),
                                currencies.getQuotes().getUsdeur())),
                                wrapper.After(wrapper.getEurrub(currencies.getQuotes().getUsdrub(),
                                currencies.getQuotes().getUsdeur())),
                                wrapper.Before(currencies.getQuotes().getUsdrub()),
                                wrapper.After(currencies.getQuotes().getUsdrub()));
                        if(currencyModel == null)
                            queryCurrency.saveCurrency(wrapper.getEurrub(currencies.getQuotes().getUsdrub(),
                                    currencies.getQuotes().getUsdeur()), wrapper.Format(currencies.getQuotes().getUsdrub(), "#.##"));
                        showOtherDataCurrency();
                    }
                    showBaseDataCurrency(queryCurrency.getLastWeather());

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
                onSettings();
            }
        });

        buttonReload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerWeather.restart();
                timerCurrency.restart();
            }
        });
    }

    public void setCity(String city) {
        this.city = city;
    }

    private void getCity() {
        setCity(city);
        if (city == null) {
            try {
                city = queryWeather.getCity();
            }
            catch (NullPointerException ex){
                ex.printStackTrace();
                city = null;
            }
        }
        if (city == null)
            city = "Yekaterinburg";
    }

    private void showBaseDataWeather (WeatherModel weatherModel) {
        if(weatherModel != null) {
            labelCity.setText(weatherModel.getCity());
            labelDegree.setText(weatherModel.getTemperature() + " °C");
        }
        else {
            labelCity.setText("Нет данных");
        }
    }

    private void showOtherDataWeather(BufferedImage Icon) {
        labelIcon.setIcon(new ImageIcon(Icon));
        labelDescription.setText(weathers.getWeather().get(0).getDescription());
        labelPressure.setText("Давление: " + wrapper.Format(wrapper.Pressure(weathers.getMain().getPressure()), "#.##") + " mmHg");
        labelHumidity.setText("Влажность: " + wrapper.Format(weathers.getMain().getHumidity(), "#.#")  + " %");
        labelSpeed.setText("Скорость ветра: " + weathers.getWind().getSpeed() + " m/s");
        labelDeg.setText("Направление ветра: " + wrapper.Degree(weathers.getWind().getDeg()));
        checkWeather.setVisible(true);
        checkWeather.setToolTipText(wrapper.Update());
    }

    private BufferedImage getIcon() throws IOException {
        return ImageIO.read(new URL("http://openweathermap.org/img/w/" + weathers.getWeather().get(0).getIcon() + ".png"));
    }

    private void showBaseDataCurrency(CurrencyModel currencyEntity) {
        if(currencyEntity != null) {
            labelCourseDollar.setText(currencyEntity.getDollar() + " \u20BD");
            labelCourseEuro.setText(currencyEntity.getEuro() + " \u20BD");
        }
        else {
            labelCourseDollar.setText("Нет данных");
        }
    }

    private void showOtherDataCurrency() {
        checkCurrency.setVisible(true);
        checkCurrency.setToolTipText(wrapper.Update());
    }

    private void onSettings() {
        mainPanel.setVisible(false);
        Settings settings = new Settings(weatherRepository, currencyRepository);
        settings.setVisible(true);
        this.dispose();
    }

    private void onHistory() {
        mainPanel.setVisible(false);
        History history = new History(weatherRepository, currencyRepository);
        history.setVisible(true);
        this.dispose();
    }
}