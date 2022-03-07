package lab2.alg;

import lab2.model.*;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final List<Event> eventList;
    private final List<Room> roomList;

    private final List<Event> eventSolution;
    private final List<Room> roomSolution;

    public Solution(Problem p) {
        this.eventList = p.getEventList();
        this.roomList = p.getRoomList();
        this.eventSolution = new ArrayList<>();
        this.roomSolution = new ArrayList<>();
    }

    public void greedySolve() {
        long t0 = System.currentTimeMillis();
        Utils.sortLists(this.eventList, this.roomList);

        for (Event event : eventList) {
            for (Room room : roomList) {
                if (event.getSize() <= room.getCapacity() && roomAvailable(event, room)) break;
            }
        }
        long t1 = System.currentTimeMillis();
        System.out.println("Execution time in ms: " + (t1 - t0));
    }

    private boolean roomAvailable(Event event, Room room) {
        int index;
        for (index = 0; index < roomSolution.size(); index++) {
            Event e = eventList.get(index);
            if (roomSolution.get(index) == room)
                if (Utils.rangesOverlap(e.getStartTime(), e.getEndTime(), event.getStartTime(), event.getEndTime()))
                    return false;
        }

        if (index == roomSolution.size()) {
            eventSolution.add(event);
            roomSolution.add(room);
            return true;
        }
        return false;
    }

    /**
     * The DSatur algorithm takes an adjacency list and colours every node based on its saturation,
     * such that no two adjacent nodes are coloured.
     * This can be applied to our problem in the following manner:
     * - the nodes are represented by our events
     * - two nodes are adjacent to each other if their times overlap
     * - the colours are the rooms associated with each event
     */
    public void DSatur() {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int index = 0; index < eventList.size(); index++) {
            adjacencyList.add(new ArrayList<>());
        }

        // preprocessing phase
        // create adjacency lists
        for (int index1 = 0; index1 < eventList.size() - 1; index1++)
            for (int index2 = index1 + 1; index2 < eventList.size(); index2++)
                if (Utils.rangesOverlap(eventList.get(index1).getStartTime(), eventList.get(index1).getEndTime(), eventList.get(index2).getStartTime(), eventList.get(index2).getEndTime())) {
                    adjacencyList.get(index1).add(index2);
                    adjacencyList.get(index2).add(index1);
                }
        
    }
}
