import java.util.*;

public class Utils {
    public static boolean existsChar(String word1, String word2) {
        for (int i = 0; i < word1.length(); i++)
            if (word2.indexOf(word1.charAt(i)) != -1)
                return true;
        return false;
    }

    public static void printMatrix(boolean[][] neighbours, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(neighbours[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printNeighbourList(List<ArrayList<Integer>> neighbours) {
        for (var list : neighbours) {
            System.out.print(neighbours.indexOf(list) + ": ");
            for (Integer neighbour : list) {
                System.out.print(neighbour + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValidSubset(Integer integer, Integer integer1, List<ArrayList<Integer>> neighbours) {
        return true;
    }
}
