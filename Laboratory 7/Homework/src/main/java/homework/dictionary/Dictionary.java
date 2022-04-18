package homework.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    static Set<String> wordList;

    public Dictionary(String path) {
        wordList = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + path + ".txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() <= 7)
                    wordList.add(line.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isWord(String word) {
        return wordList.contains(word.toUpperCase());
    }

}
