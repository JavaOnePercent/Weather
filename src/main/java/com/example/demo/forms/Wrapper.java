package com.example.demo.forms;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class Wrapper {

    public String Degree(Float degree){
        if(degree == 0.0)
            return "Cеверный";
        else if(degree > 0.0 && degree < 90.0)
            return "Северо-восточный";
        else if(degree == 90.0)
            return "Восточный";
        else if(degree > 90.0 && degree < 180.0)
            return "Юго-восточный";
        else if(degree == 180.0)
            return "Южный";
        else if(degree > 180.0 && degree < 270.0)
            return "Юго-западный";
        else if(degree == 270.0)
            return "Западный";
        else if(degree > 270.0 && degree < 360.0)
            return "Северо-западный";
        return null;
    }
    public float Format(Float number, String mask) {
        DecimalFormat df = new DecimalFormat(mask);
        number = Float.parseFloat(df.format(number).replace(',', '.'));
        return number;
    }
    public float Before(Float number) {
        return (float) (Format(number, "#.##") - 0.001);
    }
    public float After(Float number) {
        return (float) (Format(number, "#.##") + 0.001);
    }
    public float getEurrub(float usdrub, float usdeur) {
        return Format(usdrub / usdeur, "#.##");
    }
    public float Pressure(float pressure) {
        return (float) (pressure * 0.75);
    }
    public String Update() {
        return "Обновление " + new Date(System.currentTimeMillis()) + " " + new Time(System.currentTimeMillis());
    }
}
