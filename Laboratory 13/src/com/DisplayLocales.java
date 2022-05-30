package com;

import java.util.Locale;

public class DisplayLocales {
    public static void displayLocales() {
        var locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.println(locale);
        }
    }
}