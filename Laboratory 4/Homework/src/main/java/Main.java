public class Main {
    public static void main(String[] args) {
        City myCity = new City(12);

        System.out.println("Street list: ");
        myCity.printStreets();

        System.out.println("Intersection list: ");
        myCity.printIntersections();

        System.out.println("Streets with length greater than 2.75: ");
        myCity.getSpecificLengthStreets(2.75);

        System.out.println("Generated MST:");
        myCity.solveProblem();
    }
}
