/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.alg;

import lab2.model.Event;
import lab2.model.Room;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for the <code>Problem</code> and <code>Solution</code> classes,
 * created specifically for code cleanliness and decluttering of aforementioned classes.
 */
public class Utils {
    private static final int[] sizeList = new int[]{30, 100, 150};
    private static final int[] timeList = new int[]{8, 10, 12, 14, 16};
    private static final int[] timeToAddList = new int[]{2, 4, 6};
    private static final String[] OSList = new String[]{"Windows", "MacOS", "Linux", "FreeBSD"};
    private static final boolean[] projectorDecision = new boolean[]{true, false};

    public static int getRandSize() {
        return sizeList[ThreadLocalRandom.current().nextInt(0, 3)];
    }

    public static int getRandTime() {
        return timeList[ThreadLocalRandom.current().nextInt(0, 5)];
    }

    public static int getRandTime(int initTime) {
        return initTime + timeToAddList[ThreadLocalRandom.current().nextInt(0, 3)];
    }

    public static String getRandOS() {
        return OSList[ThreadLocalRandom.current().nextInt(0, 4)];
    }

    public static boolean getRandDecision() {
        return projectorDecision[ThreadLocalRandom.current().nextInt(0, 2)];
    }

    public static boolean rangesOverlap(int firstStart, int firstEnd, int secondStart, int secondEnd) {
        return firstStart < secondEnd && secondStart < firstEnd;
    }

    /**
     * Sorts <code>eventList</code> by <code>size</code> and <code>endTime</code> ascending,
     * and <code>roomList</code> by capacity ascending.
     */
    public static void sortLists(List<Event> eventList, List<Room> roomList) {
        roomList.sort(Comparator.comparingInt(Room::getCapacity));

        eventList.sort((o1, o2) -> {
            if (o1.getSize() == o2.getSize()) {
                return Integer.compare(o1.getEndTime(), o2.getEndTime());
            }
            if (o1.getSize() > o2.getSize())
                return 1;
            return -1;
        });
    }
}
