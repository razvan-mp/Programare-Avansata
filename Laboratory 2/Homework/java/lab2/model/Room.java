/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.model;

/**
 * Abstract class acting as base for the <code>LectureHall</code> and <code>ComputerLab</code> classes.
 */
public abstract class Room {
    /**
     * Name is protected so that <code>super can be used</code>
     */
    protected String name;
    /**
     * Capacity is protected so that <code>super can be used</code>
     */
    protected int capacity;

    /**
     * Constructor for <code>Room</code>, created purely for use with <code>super()</code>.
     * @param name name to be set
     * @param capacity capacity to be set
     */
    protected Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Gets <code>name</code> of the room.
     * @return current <code>name</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Gets <code>capacity</code> of the room.
     * @return current <code>capacity</code>
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Override on the <code>equals()</code> method from class <code>Object</code>, so that
     * two rooms are equal only if their names match.
     * @param o Object from class <code>Object</code> (cast to current class object)
     * @return <code>true</code> if objects are equal, <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return name.equals(room.name);
    }
}
