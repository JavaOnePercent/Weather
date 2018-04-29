package com.example.demo.api;

import com.example.demo.api.currenciesapi.Currencies;
import com.example.demo.api.weathersapi.Weathers;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {
    public String getJson(String url) {
        try {
            URL myUrl = new URL(url);
            HttpURLConnection myUrlCon = (HttpURLConnection) myUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(myUrlCon.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public String UrlApiWeather(String city) {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=168f02697ac2e075a37c762c9e2dd423&lang=ru&units=metric";
    }
    public String UrlApiCurrency() {
        return "http://www.apilayer.net/api/live?access_key=c6d1d0795016f0d2be7a271084538f26";
    }
    public Weathers APIWeather(String inputJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputJson, Weathers.class);
    }
    public Currencies APICurrency(String inputJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(inputJson, Currencies.class);
    }
}
