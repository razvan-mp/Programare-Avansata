/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.model;

/**
 * Abstract class acting as base for the <code>LectureHall</code> and <code>ComputerLab</code> classes.
 */
public abstract class Room {
    protected String name;
    protected int capacity;

    protected Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return name.equals(room.name);
    }
}
