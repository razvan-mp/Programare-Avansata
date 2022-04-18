package homework.player;

import homework.daemon.Timekeeper;
import homework.dictionary.Dictionary;
import homework.model.Board;
import homework.model.Game;
import homework.model.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private final String name;
    List<Tile> extracted = new ArrayList<>();
    private Game game;
    private int id;
    private int previousWordLength;
    private boolean isFirstRound;
    private boolean redrawTiles;

    public Player(String name) {
        this.name = name;
        this.previousWordLength = 0;
        this.isFirstRound = true;
        this.redrawTiles = false;
    }

    private void submitWord() throws InterruptedException {
        List<Tile> tilesToAdd;
        if (this.isFirstRound || this.redrawTiles) {
            extracted = game.getBag().extractTiles(7);
            this.isFirstRound = false;
        } else {
            tilesToAdd = game.getBag().extractTiles(this.previousWordLength);
            extracted.addAll(tilesToAdd);
        }

        if (extracted.isEmpty()) {
            System.out.println("Game ended.");
            System.out.println(game.getWinner());
            System.exit(0);
        }

        List<Character> characterList = new ArrayList<>();
        System.out.print("Your letters are: ");
        for (Tile tile : extracted) {
            System.out.print(tile.getLetter() + " ");
            characterList.add(tile.getLetter());
        }

        System.out.println("\nEnter a word that contains the letters above: ");

        int tryNumber = 1;

        String word = "";
        Scanner wordScanner = new Scanner(System.in);

        while (tryNumber < 4) {
            word = wordScanner.nextLine();

            if (!wordContainsLetters(word, characterList, extracted)) {
                System.out.println("Word must contain only the letters above!\nPlease Try again...\nEnter a word: ");
                tryNumber++;
            } else if (Board.getWordList().contains(word)) {
                System.out.println("Word already on board!\nPlease try again...\nEnter a word: ");
                tryNumber++;
            } else break;
        }

        if (tryNumber == 3) {
            System.out.println("You've had three wrong attempts. Your turn will be skipped and another set of tiles will be chosen.");
            this.redrawTiles = true;
            game.getBag().addTilesBack(extracted);
        } else {
            if (!Dictionary.isWord(word)) {
                System.out.println("Word is not in dictionary! Your turn will be skipped and another set of tiles will be chosen.");
                this.redrawTiles = true;
                game.getBag().addTilesBack(extracted);
            } else {
                int score = game.addScoreToPlayer(this, word);
                System.out.println("You got " + score + " points for this word!");
                game.getBoard().addWord(this, word);
                this.previousWordLength = word.length();
                sleep(50);
            }
        }
    }

    private boolean wordContainsLetters(String word, List<Character> characterList, List<Tile> extracted) {
        List<Character> wordCharacters = new ArrayList<>();

        for (int counter = 0; counter < word.length(); counter++) {
            wordCharacters.add(word.charAt(counter));
        }
        List<Character> cpy = new ArrayList<>(wordCharacters);
        wordCharacters.removeAll(characterList);

        if (wordCharacters.isEmpty()) {
            List<Tile> tilesToAddBack = new ArrayList<>();
            characterList.removeAll(cpy);
            for (Character character : characterList) {
                for (Tile tile : extracted)
                    if (tile.getLetter() == character) {
                        tilesToAddBack.add(tile);
                        break;
                    }
            }
            extracted.clear();
            extracted.addAll(tilesToAddBack);
            game.getBag().addTilesBack(tilesToAddBack);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        synchronized (game) {
            while (this.previousWordLength >= 0) {
                if (game.getCurrentPlayerId() == this.id) {
                    try {
                        Timekeeper.printCurrentTime();
                        System.out.println("Hello, " + this.name + "!\nIt is your turn...");
                        System.out.println("Words already on board: " + game.getBoard().toString());
                        submitWord();
                        Game.currentPlayer++;

                        if (Game.currentPlayer >= game.getPlayerNumber() + 1) Game.currentPlayer = 1;

                        game.notifyAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
