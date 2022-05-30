package com;

import util.Message;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info {
    public static void info(Locale locale, Message message) {
        System.out.println(message.getCountry() + " " + locale.getCountry());
        System.out.println(message.getLanguage() + " " + locale.getLanguage());
        System.out.println(message.getCurrency() + " " + Currency.getInstance(locale));
        var dateFormatSymbols = DateFormatSymbols.getInstance(locale);

        System.out.print(message.getWeek() + " ");
        for (var weekday : dateFormatSymbols.getWeekdays())
            System.out.println(weekday + ", ");

        System.out.print(message.getMonths() + " ");
        for (var month : dateFormatSymbols.getMonths())
            System.out.println(month + ", ");

        System.out.println(message.getToday() + " " + DateFormat.getDateInstance(DateFormat.MEDIUM, locale).format(new Date()));
    }
}