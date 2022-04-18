package homework.model;

import homework.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    static List<String> wordList = new ArrayList<>();

    public static List<String> getWordList() {
        return wordList;
    }

    public synchronized void addWord(Player player, String word) {
        wordList.add(word);
        System.out.println(player.getName() + ": " + word);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : wordList) {
            stringBuilder.append(s).append(" ");
        }

        return stringBuilder.toString();
    }
}
