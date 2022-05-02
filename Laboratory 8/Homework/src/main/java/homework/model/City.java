package homework.model;

import homework.entity.Entity;

public class City extends Entity {
    private final String country;
    private final boolean capital;
    private final double latitude;
    private final double longitude;

    public City(String country, String name, boolean capital, double latitude, double longitude) {
        super(name);
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public boolean isCapital() {
        return capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return name;
    }
}
