package com.example.demo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    static {
        Configuration cfg = new Configuration().configure();
        cfg.addAnnotatedClass(com.example.demo.models.WeatherEntity.class);
        cfg.addAnnotatedClass(com.example.demo.models.CurrencyEntity.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        sessionFactory = cfg.buildSessionFactory(builder.build());

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
