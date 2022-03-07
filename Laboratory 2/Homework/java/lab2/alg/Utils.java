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

    /**
     * Calculates a random size from the <code>sizeList</code> array.
     * @return random int in <code>{30, 100, 150}</code>
     */
    public static int getRandSize() {
        return sizeList[ThreadLocalRandom.current().nextInt(0, 3)];
    }

    /**
     * Calculates a random time from the <code>timeList</code> array.
     * @return random int in <code>{8, 10, 12, 14, 16}</code>
     */
    public static int getRandTime() {
        return timeList[ThreadLocalRandom.current().nextInt(0, 5)];
    }

    /**
     * Overload of <code>getRandTime()</code>; calculates a random duration and adds it to <code>initTime</code>
     * @param initTime the start time that needs to be added to
     * @return random int in <code>{8 + 2/4/6, 10 + 2/4/6, 12 + 2/4/6, 14 + 2/4/6, 16 + 2/4/6}</code>
     */
    public static int getRandTime(int initTime) {
        return initTime + timeToAddList[ThreadLocalRandom.current().nextInt(0, 3)];
    }

    /**
     * Calculates a random OS from the <code>OSList</code> array.
     * @return random String in <code>{"Windows", "MacOS", "Linux", "FreeBSD"}</code>
     */
    public static String getRandOS() {
        return OSList[ThreadLocalRandom.current().nextInt(0, 4)];
    }

    /**
     * Calculates a random decision from the <code>projectorDecision</code> array.
     * @return random boolean value in <code>{true, false}</code>
     */
    public static boolean getRandDecision() {
        return projectorDecision[ThreadLocalRandom.current().nextInt(0, 2)];
    }

    /**
     * Checks if two ranges overlap by verifying if the start of the first event is before the end of the
     * second event, and vice-versa.
     * @param firstStart lower bound of first range (start of the first event)
     * @param firstEnd upper bound of first range (end of the first event)
     * @param secondStart lower bound of second range (start of the second event)
     * @param secondEnd upper bound of second range (end of the second event)
     * @return <code>true</code> if the ranges overlap, <code>false</code> otherwise
     */
    public static boolean rangesOverlap(int firstStart, int firstEnd, int secondStart, int secondEnd) {
        return firstStart < secondEnd && secondStart < firstEnd;
    }

    /**
     * Sorts <code>eventList</code> by <code>size</code> and <code>endTime</code> ascending,
     * and <code>roomList</code> by capacity ascending.
     * @param eventList list of events to be sorted
     * @param roomList list of rooms to be sorted
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
