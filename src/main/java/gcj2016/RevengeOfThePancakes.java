package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://code.google.com/codejam/contest/6254486/dashboard
 */
public class RevengeOfThePancakes {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        // File file = new File("src/main/resources/2016/revengeofthepancakes/sample.txt");
        // File file = new File("src/main/resources/2016/revengeofthepancakes/B-small-attempt0.in");
        // File file = new File("src/main/resources/2016/revengeofthepancakes/B-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int n = 1; n <= testCases; n++) {
                String line = br.readLine();
                char[] chars = line.toCharArray();
                int nFlips = 0;
                while (!isDone(chars)) {
                    flip(chars);
                    nFlips++;
                }
                System.out.println(String.format("Case #%d: %d", n, nFlips));
            }
        }
    }

    private static void flip(char[] chars) {
        int c = chars[0];
        int i = 1;
        for (; i < chars.length; i++) {
            if (c != chars[i]) {
                break;
            }
        }
        for (int j = 0; j < i; j++) {
            if (chars[j] == '+') {
                chars[j] = '-';
            } else {
                chars[j] = '+';
            }
        }
    }

    private static boolean isDone(char[] chars) {
        for (char c : chars) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }
}
