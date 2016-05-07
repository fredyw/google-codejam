package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://code.google.com/codejam/contest/11254486/dashboard
 */
public class CloseMatch {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/2016/closematch/B-small-practice.in");
        // File file = new File("src/main/resources/2016/closematch/B-small-practice.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                String c = "";
                String j = "";
                // TODO
                System.out.println(String.format("Case #%d: %s %s", t, c, j));
            }
        }
    }
}
