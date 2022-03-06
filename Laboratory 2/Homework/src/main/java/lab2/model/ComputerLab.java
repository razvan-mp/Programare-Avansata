/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */
package lab2.model;

public class ComputerLab extends Room {
    private String computerOS;

    public ComputerLab(String name, int capacity, String computerOS) {
        super(name, capacity);
        this.computerOS = computerOS;
    }

    public String getComputerOS() {
        return computerOS;
    }

    public void setComputerOS(String computerOS) {
        this.computerOS = computerOS;
    }

    public int getCapacity() {
        return super.capacity;
    }

    public void setCapacity(int capacity) {
        super.capacity = capacity;
    }

    public String getName() {
        return super.name;
    }

    public void setName(String name) {
        super.name = name;
    }

    @Override
    public String toString() {
        return "ComputerLab{" +
                " name='" + super.name + '\'' +
                ", capacity=" + super.capacity +
                ", computerOS='" + computerOS + '\'' +
                '}';
    }
}
