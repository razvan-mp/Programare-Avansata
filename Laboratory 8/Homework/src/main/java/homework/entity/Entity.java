package homework.entity;

public abstract class Entity {
    protected String name;

    protected Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
