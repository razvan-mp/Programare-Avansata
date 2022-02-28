import java.util.ArrayList;
import java.util.List;

public class Bonus {
    public static void run(boolean[][] adjacency) {
        System.out.println("Longest word sequence has a length of " + Utils.longestPath(adjacency));
    }
}
