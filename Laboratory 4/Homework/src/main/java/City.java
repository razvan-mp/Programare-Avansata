import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class City {
    List<Street> streetList = new LinkedList<>();
    Set<Intersection> intersectionSet;

    public City(Integer streetNum) {
        Faker faker = new Faker();

        intersectionSet = IntStream.rangeClosed(0, streetNum - 1).mapToObj(i -> new Intersection(faker.address().streetName().split(" ")[1])).collect(Collectors.toSet());
        createStreetList(streetNum);
    }

    public void createStreetList(Integer streetNum) {
        Faker faker = new Faker();
        Random value = new Random();

        var intersections = intersectionSet.stream().toList();
        for (int index = 0; index <= streetNum; index++) {
            var i1Index = value.nextInt(streetNum / 2 - 1);
            var i2Index = value.nextInt(streetNum / 2, streetNum - 1);
            streetList.add(new Street(faker.address().streetName(), value.nextDouble(1.0, 5.0), intersections.get(i1Index), intersections.get(i2Index)));
        }
    }

    public void printStreets() {
        for (var street : streetList) {
            System.out.println(street.getName() + " " + Arrays.toString(street.getIntersections()));
        }
    }

    public void getSpecificLengthStreets(Double length) {
        Map<Intersection, List<Street>> cityMap = new HashMap<>();

        for (Intersection intersection : intersectionSet) {
            List<Street> intersectionStreets = new ArrayList<>();

            for (Street street : streetList)
                if (street.containsIntersection(intersection)) {
                    intersectionStreets.add(street);
                }
            cityMap.put(intersection, intersectionStreets);
        }

        streetList.stream().filter(street -> street.getLength() >= length).
                filter(street -> (cityMap.get(street.getIntersections()[0]).size() +
                        cityMap.get(street.getIntersections()[1]).size()) >= 3).
                forEach(System.out::println);
    }

    public void solveProblem() {
        Graph graph = new Graph(intersectionSet.size(), streetList.size());
        System.out.println(intersectionSet.size() + " " + streetList.size());

        List<Intersection> intersectionList = intersectionSet.stream().toList();

        for (int index = 0; index < streetList.size(); index++) {
            graph.edge[index].src = intersectionList.indexOf(streetList.get(index).getIntersections()[0]);
            graph.edge[index].dest = intersectionList.indexOf(streetList.get(index).getIntersections()[1]);
            graph.edge[index].weight = (int) (streetList.get(index).getLength());
        }
        graph.KruskalMST();
    }

    public void printIntersections() {
        for (var intersection : intersectionSet) {
            System.out.println(intersection.getName());
        }
    }
}
