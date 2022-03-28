package homework.app;

import homework.commands.*;
import homework.exceptions.InvalidNameException;
import homework.exceptions.InvalidPathException;
import homework.model.base.Article;
import homework.model.base.Book;
import homework.model.base.Catalog;
import homework.model.base.Other;
import homework.model.interfaces.Item;
import homework.utilitary.CommandHelper;
import homework.utilitary.Utils;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
/**
 * Main class for program execution; takes input from keyboard
 */
public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("");
        String identifier, title, location, year, author, type;
        Scanner myObj = new Scanner(System.in);

        System.out.print("Enter a name for the catalog: ");
        catalog.setName(myObj.nextLine());

        System.out.println("Type 'help' for a list of commands.");

        while (true) {
            System.out.print("Enter command -> ");
            String command = myObj.nextLine();

            List<String> arguments = List.of(command.split(" "));
            try {
                if (arguments.size() > 1)
                    throw new InvalidNameException("Not a valid command!");
                switch (arguments.get(0).toLowerCase()) {
                    case "add" -> {
                        System.out.print("Enter a type (Book, Article, Other): ");
                        type = myObj.nextLine();

                        type = type.toLowerCase();
                        if (CommandHelper.typeIsValid(type)) {
                            System.out.print("Enter identifier: ");
                            identifier = myObj.nextLine();
                            System.out.print("Enter title: ");
                            title = myObj.nextLine();
                            System.out.print("Enter location: ");
                            location = myObj.nextLine();

                            System.out.println("Would you like to enter additional info? (author, year of publication) [y/n]");

                            String userAnswer = myObj.nextLine();
                            if (CommandHelper.answerIsYes(userAnswer)) {
                                System.out.print("Enter year: ");
                                year = myObj.nextLine();
                                System.out.print("Enter author: ");
                                author = myObj.nextLine();
                                Utils.checkYear(year);
                            } else {
                                year = "0";
                                author = "";
                            }

                            assert catalog != null;
                            switch (type.toLowerCase()) {
                                case "book" -> {
                                    Item book = new Book(identifier, title, location, parseInt(year), author);
                                    AddCommand.execute(catalog, book);
                                    System.out.println("Item added successfully!");
                                }
                                case "article" -> {
                                    Item article = new Article(identifier, title, location, parseInt(year), author);
                                    AddCommand.execute(catalog, article);
                                    System.out.println("Item added successfully!");
                                }
                                case "other" -> {
                                    Item other = new Other(identifier, title, location, parseInt(year), author);
                                    AddCommand.execute(catalog, other);
                                    System.out.println("Item added successfully!");
                                }
                            }
                        } else {
                            throw new InvalidNameException("Not a valid type!");
                        }
                    }
                    case "save" -> {
                        String path;
                        System.out.print("Enter a path: ");
                        path = myObj.nextLine();
                        try {
                            File file = new File(path);
                            if (!file.exists())
                                throw new InvalidPathException("Not a valid path!");
                            SaveCommand.execute(catalog, path);
                        } catch (InvalidPathException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case "list" -> {
                        System.out.println("Catalog items: ");
                        assert catalog != null;
                        ListCommand.execute(catalog);
                    }
                    case "load" -> {
                        String path;
                        System.out.print("Enter a path: ");
                        path = myObj.nextLine();
                        try {
                            File file = new File(path);
                            if (!file.exists())
                                throw new InvalidPathException("Not a valid path!");
                            assert catalog != null;
                            catalog.setItemList(null);
                            catalog = null;
                            catalog = LoadCommand.execute(path);
                        } catch (InvalidPathException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case "report" -> {
                        System.out.print("Enter path to save report at: ");
                        String path = myObj.nextLine();
                        System.out.println("Generating report...");
                        ReportCommand.execute(path, catalog);
                        System.out.println("Report generated successfully for " + catalog.getName() + " in " +
                                path + "/" + catalog.getName());
                    }
                    case "view" -> {
                        System.out.print("Enter ID of object to view: ");
                        identifier = myObj.nextLine();
                        ViewCommand.execute(identifier, catalog);
                    }
                    case "info" -> {
                        System.out.print("Enter ID of object to get its info: ");
                        identifier = myObj.nextLine();
                        System.out.println("Metadata for item with ID " + identifier);
                        InfoCommand.execute(identifier, catalog);
                        InfoCommand.execute(identifier, catalog);
                    }
                    case "quit" -> System.exit(0);
                    case "help" -> {
                        System.out.println("Available commands:");
                        System.out.println("\t1. add");
                        System.out.println("\t2. save");
                        System.out.println("\t3. load");
                        System.out.println("\t4. list");
                        System.out.println("\t5. view");
                        System.out.println("\t6. report");
                        System.out.println("\t7. quit");
                    }
                    default -> Command.execute();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
