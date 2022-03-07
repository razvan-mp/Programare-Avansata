package lab2.app;
import lab2.alg.*;
import lab2.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nRandomly generated input solution: ");

        Problem p2 = new Problem( 10_000, 360, 156);
        Solution s2 = new Solution(p2);
        s2.greedySolve();
    }
}
