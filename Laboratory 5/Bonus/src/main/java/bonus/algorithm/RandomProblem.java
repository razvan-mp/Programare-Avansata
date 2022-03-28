package bonus.algorithm;

import bonus.commands.AddCommand;
import bonus.model.base.Catalog;
import bonus.model.base.Other;
import bonus.model.interfaces.Item;
import bonus.utilitary.Utils;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomProblem {
    public static void create(int numberOfItems, int numberOfConcepts) {
        Faker faker = new Faker();
        Catalog catalog = new Catalog(faker.aquaTeenHungerForce().character());

        Utils.conceptList = IntStream.rangeClosed(0, numberOfConcepts - 1).mapToObj(
                concept -> faker.internet().domainName()
        ).distinct().collect(Collectors.toList());

        List<String> listOfIds = IntStream.rangeClosed(0, numberOfItems - 1).mapToObj(
                concept -> faker.internet().uuid()
        ).distinct().toList();

        Item.setIdentifiers(new ArrayList<>());

        for (int index = 0; index < numberOfItems; index++) {
            AddCommand.execute(catalog, new Other(
                    listOfIds.get(index),
                    faker.book().title(), faker.internet().url(),
                    faker.number().numberBetween(1678, 2022), faker.book().author()
            ));
        }

        Solve.solveProblem(catalog);
    }
}
