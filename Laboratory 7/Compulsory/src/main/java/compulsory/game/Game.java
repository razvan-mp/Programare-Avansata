package compulsory.game;

import java.util.ArrayList;
import java.util.List;

import compulsory.components.*;
import compulsory.player.Player;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        this.players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }
    }

    public Bag getBag() {
        return this.bag;
    }

    public Board getBoard() {
        return this.board;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }
}
