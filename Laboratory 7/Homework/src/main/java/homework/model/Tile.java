package homework.model;

public record Tile(char letter, int points) {

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", points=" + points +
                '}';
    }
}
