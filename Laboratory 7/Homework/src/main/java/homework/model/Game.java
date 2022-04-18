package homework.model;

import homework.daemon.Timekeeper;
import homework.player.Player;
import homework.dictionary.Dictionary;

import java.util.*;

public class Game {
    public static int gameCounter = 3;
    public static int currentPlayer = 1;
    private static int playerId = 1;
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private Dictionary dictionary;
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, Integer> score = new HashMap<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() throws InterruptedException {
        System.out.println("Choose language:\n\t1. English\n\t2. Romanian\nOption: ");
        String word;
        Scanner wordScanner = new Scanner(System.in);
        word = wordScanner.nextLine().toLowerCase();

        if (word.equals("1") || word.equals("english") || word.equals("en"))
            this.dictionary = new Dictionary("wordlist");
        else if (word.equals("2") || word.equals("romanian") || word.equals("ro"))
            this.dictionary = new Dictionary("wordlist_ro");
        else {
            System.out.println("Not a valid option! Will resort to default, which is English.");
            this.dictionary = new Dictionary("wordlist");
        }

        for (Player player : players) {
            new Thread(player).start();

            player.setId(playerId);
            playerId++;

            score.put(player, 0);
        }

        Timekeeper timekeeper = new Timekeeper();
        timekeeper.setGame(this);
        Thread timekeeperThread = new Thread(timekeeper);
        timekeeperThread.setDaemon(true);
        timekeeperThread.start();
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayerId() {
        return currentPlayer;
    }

    public int getPlayerNumber() {
        return players.size();
    }

    public int addScoreToPlayer(Player player, String word) {
        int playerScore = Bag.getScore(word);
        int oldScore;

        for (Map.Entry<Player, Integer> entry : score.entrySet())
            if (entry.getKey().equals(player)) {
                oldScore = entry.getValue();
                score.replace(player, playerScore + oldScore);
                return playerScore;
            }

        return 0;
    }

    public String getWinner() {
        int maxScore = 0;
        String winner = "";

        for (Map.Entry<Player, Integer> entry : score.entrySet()) {
            if (entry.getValue() > maxScore) {
                winner = entry.getKey().getName();
                maxScore = entry.getValue();
            }
        }

        return winner + " has won the game with " + maxScore + " points.";
    }
}
