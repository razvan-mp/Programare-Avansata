/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.app;
import lab2.alg.*;

/**
 * Main class showing a solution of a randomly generated problem.
 */
public class Main {
    /**
     * Main method, declaring a problem, a solution, and calling the <code>greedySolve()</code> function
     * to calculate and display the solution.
     * @param args not used, usually null
     */
    public static void main(String[] args) {
        Problem p = new Problem(15, 6, 5);
        Solution s = new Solution(p);
        s.greedySolve();
    }
}
