package app;

import util.Message;

import java.util.Locale;
import java.util.Scanner;

import static com.DisplayLocales.displayLocales;
import static com.Info.info;
import static com.SetLocale.setLocale;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Message messageRO = new Message("res/Messages_ro.properties");
        Message messageEN = new Message("res/Messages.properties");
        while (!"exit".equals(input)) {
            if (Locale.getDefault() == Locale.forLanguageTag("ro-RO")) {
                System.out.print(messageRO.getPrompt());
            } else {
                System.out.print(messageEN.getPrompt());
            }
            input = scanner.nextLine();
            if (input.startsWith("info")) {
                Message message;
                if (Locale.getDefault() == Locale.forLanguageTag("ro-RO")) {
                    System.out.print(messageRO.getInfo());
                    message = messageRO;
                } else {
                    System.out.print(messageEN.getInfo());
                    message = messageEN;
                }
                if (input.length() > 4) {
                    String locale = input.split(" ")[1];
                    System.out.println(Locale.forLanguageTag(locale));
                    try {
                        info(Locale.forLanguageTag(locale), message);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        System.out.println(Locale.getDefault());
                        info(Locale.getDefault(), message);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (input.startsWith("set locale")) {
                String locale = input.split(" ")[2];
                setLocale(locale);
                if (Locale.getDefault() == Locale.forLanguageTag("ro-RO")) {
                    System.out.print(messageRO.getLocale());
                } else {
                    System.out.print(messageEN.getLocale());
                }
                System.out.println(locale);
            } else if ("display locales".equals(input)) {
                if (Locale.getDefault() == Locale.forLanguageTag("ro-RO")) {
                    System.out.println(messageRO.getLocales());
                } else {
                    System.out.println(messageEN.getLocales());
                }
                displayLocales();
            } else {
                if (Locale.getDefault() == Locale.forLanguageTag("ro-RO")) {
                    System.out.println(messageRO.getInvalid());
                } else {
                    System.out.println(messageEN.getInvalid());
                }
                System.out.println("Not a valid command");
            }
        }
    }
}
