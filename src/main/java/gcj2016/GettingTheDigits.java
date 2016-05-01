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
        File file = new File("src/main/resources/2016/gettingthedigits/A-small-attempt0.in");
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
        List<String> nums = Arrays.asList(ZERO, ONE, TWO, THREE, FOUR, FIVE,
            SIX, SEVEN, EIGHT, NINE);
        return getDigits(charCounts, nums, 0).digits;
    }

    private static class Result {
        private final boolean found;
        private final String digits;

        public Result(boolean found, String digits) {
            this.found = found;
            this.digits = digits;
        }
    }

    private static Result getDigits(Map<Character, Integer> charCounts, List<String> nums,
                                    int idx) {
        if (idx == nums.size()) {
            if (charCounts.isEmpty()) {
                return new Result(true, "");
            } else {
                return new Result(false, "");
            }
        }
        String num = nums.get(idx);
        boolean found = true;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (!charCounts.containsKey(c)) {
                found = false;
                // put everything back
                for (int j = 0; j < i; j++) {
                    if (charCounts.containsKey(num.charAt(j))) {
                        charCounts.put(num.charAt(j), charCounts.get(num.charAt(j)) + 1);
                    } else {
                        charCounts.put(num.charAt(j), 1);
                    }
                }
                break;
            } else {
                int count = charCounts.get(c);
                int newCount = count - 1;
                if (newCount == 0) {
                    charCounts.remove(c);
                } else {
                    charCounts.put(c, newCount);
                }
            }
        }
        String digit = (found) ? getDigit(num) : "";
        if (found) {
            Result result = getDigits(charCounts, nums, idx);
            if (result.found) {
                String digits = digit + result.digits;
                return new Result(result.found, digits);
            } else {
                digit = "";
                for (int i = 0; i < num.length(); i++) {
                    char c = num.charAt(i);
                    if (charCounts.containsKey(c)) {
                        charCounts.put(c, charCounts.get(c) + 1);
                    } else {
                        charCounts.put(c, 1);
                    }
                }
            }
        }
        Result result = getDigits(charCounts, nums, idx + 1);
        if (result.found) {
            String digits = digit + result.digits;
            return new Result(result.found, digits);
        }
        return result;
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
