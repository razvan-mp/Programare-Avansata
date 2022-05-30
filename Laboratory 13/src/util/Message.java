package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Message {
    String prompt;
    String locales;
    String locale;
    String info;
    String invalid;
    String country;
    String language;
    String currency;
    String week;
    String months;
    String today;

    public String getPrompt() {
        return prompt;
    }

    public String getLocales() {
        return locales;
    }

    public String getLocale() {
        return locale;
    }

    public String getInfo() {
        return info;
    }

    public String getInvalid() {
        return invalid;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getCurrency() {
        return currency;
    }

    public String getWeek() {
        return week;
    }

    public String getMonths() {
        return months;
    }

    public String getToday() {
        return today;
    }

    public Message(String path) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            prompt = properties.getProperty("prompt");
            locales = properties.getProperty("locales");
            locale = properties.getProperty("locale.set");
            info = properties.getProperty("info");
            invalid = properties.getProperty("invalid");
            country = properties.getProperty("country");
            language = properties.getProperty("language");
            currency = properties.getProperty("currency");
            week = properties.getProperty("week");
            months = properties.getProperty("months");
            today = properties.getProperty("today");
        }
    }
}