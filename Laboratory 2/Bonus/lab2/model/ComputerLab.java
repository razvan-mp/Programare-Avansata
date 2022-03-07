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

    public void setCapacity(int capacity) {
        super.capacity = capacity;
    }

    public void setName(String name) {
        super.name = name;
    }
}
