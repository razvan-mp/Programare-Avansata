public class Street {
    private final String name;
    private final double length;
    Intersection[] intersections;

    public Street(String name, double length, Intersection i1, Intersection i2) {
        this.intersections = new Intersection[2];
        this.intersections[0] = i1;
        this.intersections[1] = i2;
        this.name = name;
        this.length = length;
    }

    public double getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }

    public Intersection[] getIntersections() {
        return intersections;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean containsIntersection(Intersection intersection) {
        return this.intersections[0] == intersection || this.intersections[1] == intersection;
    }
}
