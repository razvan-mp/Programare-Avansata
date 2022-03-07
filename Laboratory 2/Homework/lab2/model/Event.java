/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.model;

/**
 * Class acting as an event with a name, a size, and start and end times.
 */
public class Event {
    /**
     * Protected so that it can be accessed from within another class.
     */
    protected String name;
    /**
     * Protected so that it can be accessed from within another class.
     */
    protected int size;
    /**
     * Protected so that it can be accessed from within another class.
     */
    protected int startTime;
    /**
     * Protected so that it can be accessed from within another class.
     */
    protected int endTime;

    /**
     * Sets class attributes
     * @param name name to be set
     * @param size size to be set
     * @param startTime event start time to be set
     * @param endTime event end time to be set
     */
    public Event(String name, int size, int startTime, int endTime) {
        this.name = name;
        this.size = size;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets current name of event
     * @return name of event
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets new name for event
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets size of an event
     * @return current <code>size</code>
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets new size for event
     * @param size size to be set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets start time for event
     * @return <code>startTime</code>
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Sets start time for event
     * @param startTime event start time to be set
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time for event
     * @return <code>endTime</code>
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets end time for event
     * @param endTime event end time to be set
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    /**
     * Override on the <code>equals()</code> method from class <code>Object</code>, so that
     * two events are equal only if their names match.
     * @param o Object from class <code>Object</code> (cast to current class object)
     * @return <code>true</code> if objects are equal, <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return name.equals(event.name);
    }
}
