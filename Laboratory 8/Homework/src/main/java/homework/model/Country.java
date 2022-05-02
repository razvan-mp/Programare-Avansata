package homework.model;

import homework.entity.Entity;

public class Country extends Entity {
    String continent;

    public Country(String name, String continent) {
        super(name);
        this.continent = continent;
    }

    public String getContinent() {
        return continent;
    }

    @Override
    public String toString() {
        return "homework.model.Country{" +
                "continent=" + continent +
                ", name='" + name + '\'' +
                '}';
    }
}
