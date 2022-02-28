import java.util.*;

public class Utils {
    private static int[][] dp;
    private static int rows, cols;

    public static boolean isNotNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public static boolean isNotChar(String str) {
        if (str.length() != 1) {
            return true;
        }

        try {
            Character.isLetter(str.charAt(0));
        } catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }

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


    public static int longestPath(boolean[][] adjacency) {
        rows = adjacency.length;
        cols = adjacency[0].length;
        dp = new int[rows][cols];
        int lp = 0;

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = adjacency[i][j] ? 1 : 0;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                lp = Math.max(lp, dfs(-1, i, j, matrix));
            }
        }

        return lp;
    }

    private static int dfs (int prev, int i, int j, int[][] matrix) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= prev)
            return 0;

        int cur = matrix[i][j];
        if (dp[i][j] != 0)
            return dp[i][j];

        int lp;

        int down = dfs(cur, i - 1, j, matrix);
        int top = dfs(cur, i + 1, j, matrix);
        int left = dfs(cur, i, j - 1, matrix);
        int right = dfs(cur, i, j + 1, matrix);

        lp = Math.max(down, Math.max(top, Math.max(left, right)));

        dp[i][j] = lp + 1;
        return dp[i][j];
    }
}
