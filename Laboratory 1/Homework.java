// Created by Morcov-Pahoncea Răzvan on 21 Feb 2022

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Homework {
    public static void run(String[] args) {
        long ts = System.nanoTime();
        System.out.print("Ex 2: ");
        if (args.length == 0) {
            System.out.println("Enter at least three arguments to run exercise 2!");
            return;
        } else {
            if (Utils.isNotNumber(args[0]) || Utils.isNotNumber(args[1])) {
                System.out.println("Invalid arguments! Syntax is: java Program <int> <int> <char> ... <char>");
                return;
            }
            for (int i = 2; i < args.length; i++) {
                if (Utils.isNotChar(args[i])) {
                    System.out.println("Invalid arguments! You must provide only chars as the alphabet!");
                    return;
                }
            }
        }

        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        List<String> words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < p; j++) {
                sb.append(args[ThreadLocalRandom.current().nextInt(2, args.length)]);
            }
            words.add(sb.toString());
        }


        List<ArrayList<Integer>> neighbours = new ArrayList<>(words.size());
        // boolean matrix and neighbour list of lists
        boolean[][] adjacency = new boolean[n][n];
        for (int i = 0; i < words.size(); i++) {
            neighbours.add(new ArrayList<>(words.size()));
            Arrays.fill(adjacency[i], false);
            for (int j = 0; j < words.size(); j++) {
                if (Utils.existsChar(words.get(i), words.get(j)) && i != j) {
                    adjacency[i][j] = true;
                    neighbours.get(i).add(j);
                }
            }
        }

        if (n < 30_000) {
            System.out.println("Generated words:\n" + words + "\n");
            Utils.printMatrix(adjacency, n);
            Utils.printNeighbourList(neighbours);
        }
        long tf = System.nanoTime();
        long t = tf - ts;
        System.out.println("Time in nanoseconds: " + t);

        Bonus.run(adjacency);
    }
}
