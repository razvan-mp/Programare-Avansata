/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.model;

/**
 * Class derived from <code>Room</code>, acting as a specific type of <code>Room</code>.
 */
public class LectureHall extends Room {
    private boolean hasProjector;

    /**
     * Constructor for <code>LectureHall</code>; sets attributes from base class and from <code>Room</code> using 
     * <code>super()</code>
     * @param name name to be set
     * @param capacity capacity to be set
     * @param hasProjector <code>true</code> if the Lecture Hall has a projector, <code>false otherwise</code>
     */
    public LectureHall(String name, int capacity, boolean hasProjector) {
        super(name, capacity);
        this.hasProjector = hasProjector;
    }

    /**
     * Sets the <code>capacity</code> attribute.
     * @param capacity The new <code>capacity</code> of the Lecture Hall.
     */
    public void setCapacity(int capacity) {
        super.capacity = capacity;
    }

    /**
     * Sets the <code>name</code> of the Lecture Hall.
     * @param name The new <code>name</code> of the Lecture Hall.
     */
    public void setName(String name) {
        super.name = name;
    }

    /**
     * @return if the Lecture Hall has a projector or not
     */
    public boolean doesHaveProjector() {
        return hasProjector;
    }

    /**
     * Update the projector state of a room
     * @param hasProjector <code>true</code> if projector will be added to Lecture Hall, <code>false</code> if it
     *                     will be removed
     */
    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }
}
