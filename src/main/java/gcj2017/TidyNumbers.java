package gcj2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard#s=p1
 */
public class TidyNumbers {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2017/tidynumbers/sample.txt");
        // File file = new File("src/main/resources/2017/tidynumbers/B-small-attempt0.in");
        // File file = new File("src/main/resources/2017/tidynumbers/B-small-attempt1.in");
        File file = new File("src/main/resources/2017/tidynumbers/B-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                String number = scanner.next();
                System.out.println(String.format("Case #%d: %s", t, lastTidyNumber(number)));
            }
        }
    }

    private static String lastTidyNumber(String number) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean replace = false;
        for (; i < number.length() - 1; i++) {
            int a = number.charAt(i) - '0';
            int b = number.charAt(i + 1) - '0';
            if (a <= b) {
                sb.append(a);
            } else {
                sb.append(a - 1);
                int j = i - 1;
                int count = 0;
                while (j >= 0 && number.charAt(j) == number.charAt(i)) {
                    count++;
                    j--;
                }
                if (count > 0) {
                    int k = 0;
                    for (; k < count; k++) {
                        sb.setCharAt(sb.length() - k - 1, '9');
                    }
                    sb.setCharAt(sb.length() - k - 1, (char) (sb.charAt(sb.length() - k - 1) - 1));
                }
                replace = true;
                break;
            }
        }
        if (!replace) {
            return number;
        }
        if (replace) {
            i++;
            for (; i < number.length(); i++) {
                sb.append('9');
            }
        }
        String result = sb.toString();
        if (result.startsWith("0")) {
            return result.substring(1);
        }
        return result;
    }
}
