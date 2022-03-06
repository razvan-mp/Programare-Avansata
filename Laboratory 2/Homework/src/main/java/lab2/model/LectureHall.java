/**
 * @author Răzvan-Morcov Pahoncea
 * @version
 */

package lab2.model;

public class LectureHall extends Room {
    private boolean hasProjector;

    public LectureHall(String name, int capacity, boolean hasProjector) {
        super(name, capacity);
        this.hasProjector = hasProjector;
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

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                " name='" + super.name + '\'' +
                ", capacity=" + super.capacity +
                ", hasProjector=" + hasProjector +
                '}';
    }
}
