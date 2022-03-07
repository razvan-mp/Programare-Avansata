/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */
package lab2.model;

/**
 * Class derived from <code>Room</code>, acting as a specific type of <code>Room</code>.
 */
public class ComputerLab extends Room {
    private String computerOS;

    /**
     * Sets the <code>name</code> and <code>capacity</code> attributes for the <code>super</code> class
     * (<code>Room</code>) and sets the <code>computerOS</code> attribute.
     * @param name The name (on the door) of the Computer Laboratory.
     * @param capacity The capacity (number of seats) of the Computer Laboratory.
     * @param computerOS The OS running on the computers in the Computer Laboratory.
     */
    public ComputerLab(String name, int capacity, String computerOS) {
        super(name, capacity);
        this.computerOS = computerOS;
    }

    /**
     * Gets the <code>computerOS</code> attribute.
     * @return current <code>computerOS</code>
     */
    public String getComputerOS() {
        return computerOS;
    }

    /**
     * Sets the <code>computerOS</code> attribute.
     * @param computerOS The OS that will run on the computers in the Computer Laboratory.
     */
    public void setComputerOS(String computerOS) {
        this.computerOS = computerOS;
    }

    /**
     * Sets the <code>capacity</code> attribute.
     * @param capacity The new <code>capacity</code> of the Computer Laboratory.
     */
    public void setCapacity(int capacity) {
        super.capacity = capacity;
    }

    /**
     * Sets the <code>name</code> of the Computer Laboratory.
     * @param name The new <code>name</code> of the Computer Laboratory.
     */
    public void setName(String name) {
        super.name = name;
    }
}
