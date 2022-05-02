package homework.model;

import homework.entity.Entity;

public class Continent extends Entity {

    public Continent(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "homework.model.Continent{" +
                "name='" + name + '\'' +
                '}';
    }
}
