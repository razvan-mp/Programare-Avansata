package homework.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {
    private static final List<Tile> tileList = new ArrayList<>();
    private static final List<Tile> referenceList = new ArrayList<>();

    public Bag() {
        addTiles();
    }

    public static int getScore(String word) {
        word = word.toUpperCase();
        int playerScore = 0;
        for (int counter = 0; counter < word.length(); counter++) {
            for (Tile tile : referenceList)
                if (tile.getLetter() == word.charAt(counter)) {
                    playerScore += tile.getPoints();
                    break;
                }
        }

        return playerScore;
    }

    private void addToBag(char characterToAdd, int numberOfTiles, int numberOfPoints) {
        for (int counter = 0; counter < numberOfTiles; counter++) {
            tileList.add(new Tile(characterToAdd, numberOfPoints));
        }
        referenceList.add(new Tile(characterToAdd, numberOfPoints));
    }

    public void addTilesBack(List<Tile> tileList) {
        Bag.tileList.addAll(tileList);
    }

    public void addTiles() {
        for (char character = 'A'; character <= 'Z'; character++)
            switch (character) {
                case 'A', 'O' -> addToBag(character, 8, 1);
                case 'B', 'C', 'M', 'P' -> addToBag(character, 2, 3);
                case 'D' -> addToBag(character, 4, 2);
                case 'E' -> addToBag(character, 12, 1);
                case 'F', 'H', 'V', 'W', 'Y' -> addToBag(character, 2, 4);
                case 'G' -> addToBag(character, 3, 2);
                case 'I' -> addToBag(character, 9, 1);
                case 'J', 'X' -> addToBag(character, 1, 8);
                case 'K' -> addToBag(character, 1, 5);
                case 'L', 'S', 'U' -> addToBag(character, 4, 1);
                case 'N', 'R', 'T' -> addToBag(character, 6, 1);
                case 'Q', 'Z' -> addToBag(character, 1, 10);
            }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();

        for (int index = 0; index < howMany; index++) {
            if (tileList.isEmpty()) {
                System.out.println("No tiles to extract. Game will end.");
                return Collections.emptyList();
            }

            int randomTile = ThreadLocalRandom.current().nextInt(0, tileList.size());
            extracted.add(tileList.get(randomTile));
            tileList.remove(tileList.get(randomTile));
        }

        return extracted;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Tile tile : tileList)
            str.append(tile.getLetter()).append(" ").append(tile.getPoints()).append("\n");

        return str.toString();
    }

    public List<Tile> getTileList() {
        return tileList;
    }
}
