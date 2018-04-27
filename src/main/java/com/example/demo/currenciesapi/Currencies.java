package com.example.demo.currenciesapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {
    private Quotes quotes;

    public Quotes getQuotes(){
        return quotes;
    }
    public void setQuotes(Quotes quotes){
        this.quotes = quotes;
    }
}
