package com.example.demo;

import com.example.demo.currenciesapi.Currencies;
import com.example.demo.models.CurrencyEntity;
import com.example.demo.models.WeatherEntity;
import com.example.demo.weathersAPI.Weathers;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.List;

public class SberTech {
    public String json(String url) {
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

    @SuppressWarnings("unchecked")
    public void TransactSaveWeather(Weathers weathers) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<WeatherEntity> weatherLast = null;
        try{
            session.beginTransaction();

            Object _count = session.createCriteria(WeatherEntity.class).setProjection(Projections.count("id")).uniqueResult();
            Long count = (Long) _count;
            Criteria criteria = session.createCriteria(WeatherEntity.class);
            criteria.add(Restrictions.eq("city", weathers.getName()));
            criteria.add(Restrictions.eq("temperature", weathers.getMain().getTemp()));
            criteria.add(Restrictions.eq("id", count));
            weatherLast = criteria.list();
            if(weatherLast.isEmpty()) {
                WeatherEntity weatherEntity = new WeatherEntity();
                weatherEntity.setDay(new Date(System.currentTimeMillis()));
                weatherEntity.setTime(new Time(System.currentTimeMillis()));
                weatherEntity.setCity(weathers.getName());
                weatherEntity.setTemperature(weathers.getMain().getTemp());
                session.saveOrUpdate(weatherEntity);
                session.flush();
            }
            session.getTransaction().commit();
            System.out.println("Good");
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            //sessionFactory.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void TransactSaveCurrency(Currencies currencies) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<CurrencyEntity> currencyLast = null;
        try{
            session.beginTransaction();

            Object _count = session.createCriteria(CurrencyEntity.class).setProjection(Projections.count("id")).uniqueResult();
            Long count = (Long) _count;
            Criteria criteria = session.createCriteria(CurrencyEntity.class);
            Float euro = Format(Eurrub(currencies.getQuotes().getUsdrub(), currencies.getQuotes().getUsdeur()), "#.##");
            Float dollar = Format(currencies.getQuotes().getUsdrub(), "#.##");
            criteria.add(Restrictions.like("dollar", dollar));
            criteria.add(Restrictions.like("euro", euro));
            criteria.add(Restrictions.eq("id", count));
            currencyLast = criteria.list();
            if(currencyLast.isEmpty()) {
                CurrencyEntity currencyEntity = new CurrencyEntity();
                currencyEntity.setDay(new Date(System.currentTimeMillis()));
                currencyEntity.setTime(new Time(System.currentTimeMillis()));
                currencyEntity.setDollar(Format(currencies.getQuotes().getUsdrub(), "#.##"));
                currencyEntity.setEuro(Format(Eurrub(currencies.getQuotes().getUsdrub(), currencies.getQuotes().getUsdeur()), "#.##"));
                session.saveOrUpdate(currencyEntity);
                session.flush();
            }
            session.getTransaction().commit();
            System.out.println("Good");
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            //sessionFactory.close();
        }

    }

    @SuppressWarnings("unchecked")
    public WeatherEntity TransactWeatherMain() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<WeatherEntity> weathers = null;
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(WeatherEntity.class);
            criteria.addOrder(Order.desc("id"));
            criteria.setMaxResults(1);
            weathers = criteria.list();
            session.getTransaction().commit();
            System.out.println("Good");
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            //sessionFactory.close();
        }
        if(weathers.isEmpty())
            return null;
        else
            return weathers.get(0);
    }

    @SuppressWarnings("unchecked")
    public CurrencyEntity TransactCurrencyMain() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<CurrencyEntity> currencies = null;
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CurrencyEntity.class);
            criteria.addOrder(Order.desc("id"));
            criteria.setMaxResults(1);
            currencies = criteria.list();
            session.getTransaction().commit();
            System.out.println("Good");
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            //sessionFactory.close();
        }
        if(currencies.isEmpty())
            return null;
        else
            return currencies.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<WeatherEntity> TransactWeatherTable() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<WeatherEntity> weathers = null;
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(WeatherEntity.class);
            criteria.addOrder(Order.desc("day"));
            criteria.addOrder(Order.desc("time"));
            weathers = criteria.list();
            session.getTransaction().commit();
            System.out.println("Good");
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            //sessionFactory.close();
        }
        return weathers;
    }
    @SuppressWarnings("unchecked")
    public List<CurrencyEntity> TransactCurrencyTable() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<CurrencyEntity> currencies = null;
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CurrencyEntity.class);
            criteria.addOrder(Order.desc("day"));
            criteria.addOrder(Order.desc("time"));
            currencies = criteria.list();
            session.getTransaction().commit();
            System.out.println("Good");
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            session.close();
            //sessionFactory.close();
        }
        return currencies;
    }

    @SuppressWarnings("unchecked")
    public String TransactGetCity() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<WeatherEntity> weathers = null;
        String city = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(WeatherEntity.class);
            criteria.addOrder(Order.desc("id"));
            criteria.setMaxResults(1);
            weathers = criteria.list();
            if(!weathers.isEmpty()) {
                city = weathers.get(0).getCity();
            }
            session.getTransaction().commit();
            System.out.println("Good");
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            //sessionFactory.close();
        }
        return city;
    }
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
    public float Eurrub(float usdrub, float usdeur) {
        return usdrub / usdeur;
    }
    public float Pressure(float pressure) {
        return (float) (pressure * 0.75);
    }
    public String Update() {
        return "Обновление " + new Date(System.currentTimeMillis()) + " " + new Time(System.currentTimeMillis());
    }


}