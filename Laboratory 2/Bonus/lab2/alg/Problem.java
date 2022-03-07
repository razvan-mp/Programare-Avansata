package lab2.alg;

import lab2.model.ComputerLab;
import lab2.model.Event;
import lab2.model.LectureHall;
import lab2.model.Room;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private List<Event> eventList;
    private List<Room> roomList;

    public Problem(int eventNumber, int computerLabNumber, int lectureHallNumber) {
        populateLists(eventNumber, computerLabNumber, lectureHallNumber);
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

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
