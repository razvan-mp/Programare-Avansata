package com;

import java.util.Locale;

public class SetLocale {
    public static void setLocale(String locale) {
        Locale myLocale = Locale.forLanguageTag(locale);
        Locale.setDefault(myLocale);
    }
}