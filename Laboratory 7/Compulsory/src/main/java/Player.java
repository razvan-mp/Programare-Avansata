import java.util.Collections;
import java.util.List;

public class Player implements Runnable {
    private String name;
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
        System.out.println("It is your turn!\nEnter word: ");
        this.submitWord();
    }
}
