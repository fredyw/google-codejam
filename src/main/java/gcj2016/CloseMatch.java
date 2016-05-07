package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://code.google.com/codejam/contest/11254486/dashboard
 */
public class CloseMatch {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2016/closematch/sample.txt");
        File file = new File("src/main/resources/2016/closematch/B-small-practice.in");
        // File file = new File("src/main/resources/2016/closematch/B-large-practice.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                String[] line = br.readLine().split("\\s+");
                String coder = line[0];
                String jammer = line[1];
                String closeMatch = closeMatch(coder, jammer);
                System.out.println(String.format("Case #%d: %s", t, closeMatch));
            }
        }
    }

    private enum Score {
        CODER_JAMMER_EQUAL,
        CODER_GREATER,
        JAMMER_GREATER
    }

    private static String closeMatch(String coder, String jammer) {
        StringBuilder coderScore = new StringBuilder();
        StringBuilder jammerScore = new StringBuilder();
        Score score = Score.CODER_JAMMER_EQUAL;
        for (int i = 0; i < coder.length(); i++) {
            char c = coder.charAt(i);
            char j = jammer.charAt(i);
            if (score == Score.CODER_JAMMER_EQUAL) {
                if (c == '?' && j == '?') {
                    coderScore.append('0');
                    jammerScore.append('0');
                } else if (c == '?' && j != '?') {
                    coderScore.append(j);
                    jammerScore.append(j);
                } else if (c != '?' && j == '?') {
                    coderScore.append(c);
                    jammerScore.append(c);
                } else {
                    if (c < j) {
                        score = Score.JAMMER_GREATER;
                    } else if (c > j) {
                        score = Score.CODER_GREATER;
                    }
                    coderScore.append(c);
                    jammerScore.append(j);
                }
            } else if (score == Score.CODER_GREATER) {
                if (c == '?' && j == '?') {
                    coderScore.append('0');
                    jammerScore.append('9');
                } else if (c == '?' && j != '?') {
                    coderScore.append('0');
                    jammerScore.append(j);
                } else if (c != '?' && j == '?') {
                    coderScore.append(c);
                    jammerScore.append('9');
                } else {
                    coderScore.append(c);
                    jammerScore.append(j);
                }
            } else if (score == Score.JAMMER_GREATER) {
                if (c == '?' && j == '?') {
                    coderScore.append('9');
                    jammerScore.append('0');
                } else if (c == '?' && j != '?') {
                    coderScore.append('9');
                    jammerScore.append(j);
                } else if (c != '?' && j == '?') {
                    coderScore.append(c);
                    jammerScore.append('0');
                } else {
                    coderScore.append(c);
                    jammerScore.append(j);
                }
            }
        }
        return coderScore + " " + jammerScore;
    }
}
