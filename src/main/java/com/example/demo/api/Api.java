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
    public Weathers APIWeather(String inputJson) throws IOException {
        Weathers weathers = null;
        ObjectMapper mapper = new ObjectMapper();
        weathers = mapper.readValue(inputJson, Weathers.class);
        return weathers;
    }
    public Currencies APICurrency(String inputJson) throws IOException {
        Currencies currencies = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        currencies = mapper.readValue(inputJson, Currencies.class);
        return currencies;
    }
}
