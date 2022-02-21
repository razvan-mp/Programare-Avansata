// Created by Morcov-Pahoncea Răzvan on 21 Feb 2022
public class Compulsory {
    public static void run() {
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);

        n *= 3;
        n += Integer.parseInt("10101", 2) + Integer.parseInt("FF", 16);
        n *= 6;

        int result = 0;
        while (n > 0 || result > 9)
        {
            if (n == 0) {
                n = result;
                result = 0;
            }
            result += n % 10;
            n /= 10;
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
