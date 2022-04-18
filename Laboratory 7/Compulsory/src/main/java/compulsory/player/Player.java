package compulsory.player;

import compulsory.components.Tile;
import compulsory.game.Game;

import java.util.Collections;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private boolean running;

    public Player(String name) {
        this.name = name;
    }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        Collections.shuffle(extracted);
        if (extracted.isEmpty())
            return false;
        String word = "";

        for (Tile tile : extracted) {
            word += tile.getLetter();
        }
        game.getBoard().addWord(this, word);
        return true;
    }

    public String getName() {
        return name;
    }

    public void setGame(Game game) {
        this.game = game;
        running = true;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
