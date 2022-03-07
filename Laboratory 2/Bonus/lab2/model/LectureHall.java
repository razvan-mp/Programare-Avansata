package lab2.model;

public class LectureHall extends Room {
    private boolean hasProjector;

    public LectureHall(String name, int capacity, boolean hasProjector) {
        super(name, capacity);
        this.hasProjector = hasProjector;
    }

    public void setCapacity(int capacity) {
        super.capacity = capacity;
    }

    public void setName(String name) {
        super.name = name;
    }

    public boolean doesHaveProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }
}
