package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheLastWord {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2016/thelastword/sample.txt");
        // File file = new File("src/main/resources/2016/thelastword/A-small-attempt0.in");
        File file = new File("src/main/resources/2016/thelastword/A-large.in");
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
        List<String> result = new ArrayList<>();
        lastWord(str, 0, "", result);
        Collections.sort(result);
        return result.get(result.size() - 1);
    }

    private static void lastWord(String str, int idx, String accu, List<String> result) {
        if (idx == str.length()) {
            result.add(accu);
            return;
        }
        char c = str.charAt(idx);
        int newIdx = idx + 1;
        String a = c + accu;
        String b = accu + c;
        if (a.compareTo(b) < 0) {
            lastWord(str, newIdx, accu + c, result);
        } else if (a.compareTo(b) == 0) {
            lastWord(str, newIdx, c + accu, result);
            lastWord(str, newIdx, accu + c, result);
        } else if (a.compareTo(b) > 0) {
            lastWord(str, newIdx, c + accu, result);
        }
    }
}
