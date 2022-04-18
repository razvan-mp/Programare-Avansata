package compulsory.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {
    private final List<Tile> tileList;

    public Bag() {
        this.tileList = new ArrayList<>();

        for (Character c = 'a'; c < 'z' ; c++)
            tileList.add(new Tile(c, ThreadLocalRandom.current().nextInt(1, 13)));
    }

    public synchronized List<Tile> extractTiles(int numberOfTiles) {
        Random random = new Random();
        Integer randomIndex = 0;
        List<Tile> extractedTiles = new ArrayList<>();
        for (int tileNumber = 0; tileNumber < numberOfTiles; tileNumber++) {
            if (tileList.isEmpty())
                break;
            randomIndex = random.nextInt(0, tileList.size());
            extractedTiles.add(tileList.get(randomIndex));
        }
        return extractedTiles;
    }
}
