package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://code.google.com/codejam/contest/4304486/dashboard
 */
public class TheLastWord {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2016/thelastword/sample.txt");
        // File file = new File("src/main/resources/2016/thelastword/A-small-attempt0.in");
        // File file = new File("src/main/resources/2016/thelastword/A-large.in");
        File file = new File("src/main/resources/2016/thelastword/A-large-practice.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                String str = br.readLine();
                String lastWord = lastWord(str);
                System.out.println(String.format("Case #%d: %s", t, lastWord));
            }
        }
    }

    private static String lastWord(String str) {
        String lastWord = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (lastWord.isEmpty()) {
                lastWord += str.charAt(i);
            } else {
                String a = c + lastWord;
                String b = lastWord + c;
                if (a.compareTo(b) < 0) {
                    lastWord = b;
                } else {
                    lastWord = a;
                }
            }
        }
        return lastWord;
    }
}
