public record Intersection(String name) {

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
