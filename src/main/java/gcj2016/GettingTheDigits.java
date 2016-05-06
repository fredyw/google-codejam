package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://code.google.com/codejam/contest/11254486/dashboard
 */
public class GettingTheDigits {
    private static final String ZERO = "ZERO";
    private static final String ONE = "ONE";
    private static final String TWO = "TWO";
    private static final String THREE = "THREE";
    private static final String FOUR = "FOUR";
    private static final String FIVE = "FIVE";
    private static final String SIX = "SIX";
    private static final String SEVEN = "SEVEN";
    private static final String EIGHT = "EIGHT";
    private static final String NINE = "NINE";

    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2016/gettingthedigits/sample.txt");
        // File file = new File("src/main/resources/2016/gettingthedigits/A-small-attempt0.in");
        // File file = new File("src/main/resources/2016/gettingthedigits/A-small-practice.in");
        File file = new File("src/main/resources/2016/gettingthedigits/A-large-practice.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                String line = br.readLine();
                String digits = getDigits(line);
                System.out.println(String.format("Case #%d: %s", t, digits));
            }
        }
    }

    private static Map<Character, Integer> getCharCounts(String chars) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            if (charCounts.containsKey(c)) {
                charCounts.put(c, charCounts.get(c) + 1);
            } else {
                charCounts.put(c, 1);
            }
        }
        return charCounts;
    }

    private static String getDigits(String chars) {
        Map<Character, Integer> charCounts = getCharCounts(chars);
        return getDigits(charCounts);
    }

    private static String getDigits(Map<Character, Integer> charCounts) {
        Map<String, Integer> digits = new HashMap<>();
        // a single character that can determine the number
        // stage 1
        if (charCounts.containsKey('Z')) {
            int count = charCounts.get('Z');
            digits.put(ZERO, count);
            updateCharCounts(charCounts, ZERO, count);
        }
        if (charCounts.containsKey('W')) {
            int count = charCounts.get('W');
            digits.put(TWO, count);
            updateCharCounts(charCounts, TWO, count);
        }
        if (charCounts.containsKey('X')) {
            int count = charCounts.get('X');
            digits.put(SIX, count);
            updateCharCounts(charCounts, SIX, count);
        }
        if (charCounts.containsKey('G')) {
            int count = charCounts.get('G');
            digits.put(EIGHT, count);
            updateCharCounts(charCounts, EIGHT, count);
        }
        // stage 2
        if (charCounts.containsKey('H')) {
            int count = charCounts.get('H');
            digits.put(THREE, count);
            updateCharCounts(charCounts, THREE, count);
        }
        if (charCounts.containsKey('U')) {
            int count = charCounts.get('U');
            digits.put(FOUR, count);
            updateCharCounts(charCounts, FOUR, count);
        }
        if (charCounts.containsKey('S')) {
            int count = charCounts.get('S');
            digits.put(SEVEN, count);
            updateCharCounts(charCounts, SEVEN, count);
        }
        // stage 3
        if (charCounts.containsKey('O')) {
            int count = charCounts.get('O');
            digits.put(ONE, count);
            updateCharCounts(charCounts, ONE, count);
        }
        if (charCounts.containsKey('F')) {
            int count = charCounts.get('F');
            digits.put(FIVE, count);
            updateCharCounts(charCounts, FIVE, count);
        }
        // stage 4
        if (charCounts.containsKey('I')) {
            int count = charCounts.get('I');
            digits.put(NINE, count);
            updateCharCounts(charCounts, NINE, count);
        }

        StringBuilder result = new StringBuilder();
        List<String> nums = Arrays.asList(ZERO, ONE, TWO, THREE, FOUR, FIVE,
            SIX, SEVEN, EIGHT, NINE);
        for (String num : nums) {
            if (digits.containsKey(num)) {
                int count = digits.get(num);
                for (int i = 0; i < count; i++){
                    result.append(getDigit(num));
                }
            }
        }
        return result.toString();
    }

    private static void updateCharCounts(Map<Character, Integer> charCounts, String num,
                                         int count) {
        for (int c = 0; c < count; c++) {
            for (int i = 0; i < num.length(); i++) {
                char ch = num.charAt(i);
                int cnt = charCounts.get(ch);
                int newCount = cnt - 1;
                if (newCount == 0) {
                    charCounts.remove(ch);
                } else {
                    charCounts.put(ch, newCount);
                }
            }
        }
    }

    private static String getDigit(String num) {
        if (ZERO.equals(num)) {
            return "0";
        } else if (ONE.equals(num)) {
            return "1";
        } else if (TWO.equals(num)) {
            return "2";
        } else if (THREE.equals(num)) {
            return "3";
        } else if (FOUR.equals(num)) {
            return "4";
        } else if (FIVE.equals(num)) {
            return "5";
        } else if (SIX.equals(num)) {
            return "6";
        } else if (SEVEN.equals(num)) {
            return "7";
        } else if (EIGHT.equals(num)) {
            return "8";
        } else if (NINE.equals(num)) {
            return "9";
        }
        throw new RuntimeException("Invalid num: " + num);
    }
}
