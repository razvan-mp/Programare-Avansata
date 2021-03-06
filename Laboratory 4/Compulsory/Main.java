import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Intersection[] nodes = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> new Intersection("v" + i))
                .toArray(Intersection[]::new);

        Set<Intersection> intersectionSet = new HashSet<>(Arrays.stream(nodes).toList());

        intersectionSet.add(new Intersection("v3"));
        System.out.println(intersectionSet);

        List<Street> streetList = new LinkedList<>();
        streetList.add(new Street("s1", 2, nodes[0], nodes[1]));
        streetList.add(new Street("s2", 2, nodes[0], nodes[6]));
        streetList.add(new Street("s3", 2, nodes[0], nodes[7]));
        streetList.add(new Street("s4", 2, nodes[1], nodes[7]));
        streetList.add(new Street("s5", 1, nodes[7], nodes[6]));
        streetList.add(new Street("s6", 3, nodes[1], nodes[2]));
        streetList.add(new Street("s7", 2, nodes[7], nodes[8]));
        streetList.add(new Street("s8", 2, nodes[7], nodes[5]));
        streetList.add(new Street("s9", 3, nodes[6], nodes[5]));
        streetList.add(new Street("s10", 1, nodes[2], nodes[5]));
        streetList.add(new Street("s11", 1, nodes[2], nodes[3]));
        streetList.add(new Street("s12", 1, nodes[3], nodes[4]));
        streetList.add(new Street("s13", 3, nodes[4], nodes[5]));
        streetList.add(new Street("s14", 1, nodes[3], nodes[8]));
        streetList.add(new Street("s15", 1, nodes[8], nodes[4]));

        // method reference sort for street lengths
        streetList.sort(Comparator.comparingDouble(Street::getLength));

        System.out.println(streetList);
    }

}
