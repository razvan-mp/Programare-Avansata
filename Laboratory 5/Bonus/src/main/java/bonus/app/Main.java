package bonus.app;

import bonus.algorithm.RandomProblem;
import bonus.algorithm.Solve;
import bonus.commands.*;
import bonus.model.base.Book;
import bonus.model.base.Catalog;
import bonus.utilitary.Utils;
import com.github.javafaker.Faker;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("myCatalog");

        AddCommand.execute(catalog, new Book("knuth16", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth17", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth18", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth19", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth20", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth21", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth22", "Intro to Java", "https://www.google.com", 2009, "John K."));
        AddCommand.execute(catalog, new Book("knuth23", "Intro to Java", "https://www.google.com", 2009, "John K."));

        // pre-made problem
        Solve.solveProblem(catalog);

        Utils.conceptList = null;

        RandomProblem.create(100, 25);
    }
}
