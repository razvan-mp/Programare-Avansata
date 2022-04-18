package homework.daemon;

import static java.lang.Thread.sleep;

import homework.model.Game;

public class Timekeeper implements Runnable {
    Game game;
    private static int minutes;
    private static int seconds;

    public Timekeeper() {
        minutes = 2;
        seconds = 0;
    }

    public static void printCurrentTime() {
        if (seconds < 10) {
            System.out.println("Game time: 0" + minutes + ":0" + seconds);
            System.out.println();
        }
        else {
            System.out.println("Game time: 0" + minutes + ":" + seconds);
            System.out.println();
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void run() {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        minutes--;
        seconds = 60;

        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds--;
            if (seconds == 0) {
                minutes--;
                seconds = 60;
            }

            if (minutes < 0) {
                System.out.println("Game ended. No time remaining.");
                System.out.println(game.getWinner());
                System.exit(0);
            }
        }
    }
}
