/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.app;
import lab2.alg.*;
import lab2.model.*;

import java.util.ArrayList;
import java.util.List;

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
        Event c1 = new Event("C1", 100, 8, 10);
        Event c2 = new Event("C2", 100, 10, 12);
        Event l1 = new Event("L1", 30, 8, 10);
        Event l2 = new Event("L2", 30, 8, 10);
        Event l3 = new Event("L3", 30, 10, 12);

        Room r1 = new ComputerLab("401", 30, "FreeBSD");
        Room r2 = new ComputerLab("403", 30, "Windows");
        Room r3 = new ComputerLab("405", 30, "Windows");
        Room r4 = new LectureHall("309", 100, true);

        List<Event> eventList = new ArrayList<>();
        eventList.add(c1);
        eventList.add(c2);
        eventList.add(l1);
        eventList.add(l2);
        eventList.add(l3);

        List<Room> roomList = new ArrayList<>();
        roomList.add(r1);
        roomList.add(r2);
        roomList.add(r3);
        roomList.add(r4);

        System.out.println("Lab example input solution: ");

        Problem p1 = new Problem(eventList, roomList);
        Solution s1 = new Solution(p1);
        s1.greedySolve();

        System.out.println("\nRandomly generated input solution: ");

        Problem p2 = new Problem(15, 6, 5);
        Solution s2 = new Solution(p2);
        s2.greedySolve();
    }
}
