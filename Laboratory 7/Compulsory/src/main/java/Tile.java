public class Tile {
    private final Character letter;
    private final Integer points;

    public Tile(Character letter, Integer points) {
        this.letter = letter;
        this.points = points;
    }

    public Character getLetter() {
        return letter;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Tile " + letter +
                ", points =" + points;
    }
}
