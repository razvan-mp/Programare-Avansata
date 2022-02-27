import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Bonus {
    public static void run(List<String> words, List<ArrayList<Integer>> neighbours) {
        // * Adding indexes to the list so that I can verify if Wk+1 = W1
        System.out.println("Ex 3 (Bonus):");
        for (int i = 0; i < neighbours.size(); i++) {
            neighbours.get(i).add(0, i);
        }
        neighbours.sort((Comparator<ArrayList<?>>) (a1, a2) -> a2.size() - a1.size());

        for (var list : neighbours) {
            if (Utils.isValidSubset(list.get(0), list.get(list.size() - 1), neighbours)) {
                System.out.print("Found subset: { ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(words.get(i) + " (" + list.get(i) + ") ");
                }
                System.out.println("}");
                return;
            }
        }
        System.out.println("There is no subset such that the conditions are validated.");
    }
}
