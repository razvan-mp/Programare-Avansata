/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.alg;

import lab2.model.ComputerLab;
import lab2.model.Event;
import lab2.model.LectureHall;
import lab2.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem class that generates two lists with events and rooms,
 * readying them for the Solution class.
 */
public class Problem {
    private List<Event> eventList;
    private List<Room> roomList;

    /**
     * Class constructor, taking three user-given numbers
     * as parameters and immediately calling the <code>populateLists</code> method.
     * @param eventNumber number of events to be generated
     * @param computerLabNumber number of Computer Laboratories to be generated
     * @param lectureHallNumber number of Lecture Halls to be generated
     */
    public Problem(int eventNumber, int computerLabNumber, int lectureHallNumber) {
        populateLists(eventNumber, computerLabNumber, lectureHallNumber);
    }

    /**
     * Class constructor, taking two lists as arguments.
     * @param eventList list of events given as input
     * @param roomList list of rooms given as inputs
     */
    public Problem(List<Event> eventList, List<Room> roomList) {
        this.eventList = eventList;
        this.roomList = roomList;
    }

    /**
     * Gets a list of events
     * @return list of events
     */
    public List<Event> getEventList() {
        return eventList;
    }

    /**
     * Gets a list of rooms
     * @return list of rooms
     */
    public List<Room> getRoomList() {
        return roomList;
    }

    /**
     * Generate events and rooms with random specifications (but with predefined values, for practical
     * reasons); uses the <code>Utils</code> class for cleaner code.
     */
    private void populateLists(int eventNumber, int computerLabNumber, int lectureHallNumber) {
        this.eventList = new ArrayList<>();
        this.roomList = new ArrayList<>();

        int numIndicator = 0, startTime;
        String name;

        for (int i = 0; i < eventNumber; i++) {
            name = "Event " + numIndicator;
            startTime = Utils.getRandTime();
            eventList.add(new Event(name, Utils.getRandSize(), startTime, Utils.getRandTime(startTime)));
            numIndicator++;
        }

        numIndicator = 0;
        for (int i = 0; i < computerLabNumber; i++) {
            name = "Computer Lab " + numIndicator;
            roomList.add(new ComputerLab(name, Utils.getRandSize(), Utils.getRandOS()));
            numIndicator++;
        }

        numIndicator = 0;
        for (int i = 0; i < lectureHallNumber; i++) {
            name = "Lecture Hall " + numIndicator;
            roomList.add(new LectureHall(name, Utils.getRandSize(), Utils.getRandDecision()));
            numIndicator++;
        }
    }
}
