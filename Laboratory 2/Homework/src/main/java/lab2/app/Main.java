/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.app;
import lab2.alg.*;

/**
 * Main class showing a solution of a randomly generated
 * problem and one of a doctored input (made with edge-cases
 * in mind).
 */
public class Main {
    public static void main(String[] args) {
        Problem p = new Problem(15, 6, 5);
        Solution s = new Solution(p);
        s.greedySolve();
    }
}
