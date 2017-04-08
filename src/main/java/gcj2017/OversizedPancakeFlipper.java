package gcj2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard#s=p0
 */
public class OversizedPancakeFlipper {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2017/oversizedpancakeflipper/sample.txt");
        // File file = new File("src/main/resources/2017/oversizedpancakeflipper/A-small-attempt0.in");
        File file = new File("src/main/resources/2017/oversizedpancakeflipper/A-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                char[] pancakes = scanner.next().toCharArray();
                int k = scanner.nextInt();
                System.out.println(String.format("Case #%d: %s", t, pancakeFlipper(pancakes, k)));
            }
        }
    }

    private static String pancakeFlipper(char[] pancakes, int k) {
        int flip = 0;
        for (int i = 0; i < pancakes.length; i++) {
            char c = pancakes[i];
            if (c == '-') {
                if (i + k > pancakes.length) {
                    continue;
                }
                for (int j = i; j < i + k; j++) {
                    pancakes[j] = (pancakes[j] == '+') ? '-' : '+';
                }
                flip++;
            }
        }
        if (!allHappy(pancakes)) {
            return "IMPOSSIBLE";
        }
        return Integer.toString(flip);
    }

    private static boolean allHappy(char[] pancakes) {
        for (int i = 0; i < pancakes.length; i++) {
            if (pancakes[i] != '+') {
                return false;
            }
        }
        return true;
    }
}
