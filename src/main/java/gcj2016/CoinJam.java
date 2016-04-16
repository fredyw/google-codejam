package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringJoiner;

/**
 * https://code.google.com/codejam/contest/6254486/dashboard
 */
public class CoinJam {
    public static void main(String[] args) throws Exception {
        // File file = new File(args[0]);
        // File file = new File("src/main/resources/2016/coinjam/sample.txt");
        File file = new File("src/main/resources/2016/coinjam/C-small-attempt0.in");
        // File file = new File("src/main/resources/2016/coinjam/B-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                System.out.println(String.format("Case #%d:", t));
                String[] parts = br.readLine().split("\\s+");
                int n = Integer.parseInt(parts[0]);
                int j = Integer.parseInt(parts[1]);
                int size = n - 2;
                int num = 0;
                int i = 0;
                newNumber:
                while (i < j) {
                    String numStr = generateNumberString(num++, size);
                    StringJoiner divisors = new StringJoiner(" ");
                    for (int base = 2; base <= 10; base++) {
                        long val = Long.parseLong(numStr, base);
                        Divisor divisor = findDivisor(val);
                        if (!divisor.found) {
                            continue newNumber;
                        } else {
                            divisors.add(Long.toString(divisor.number));
                        }
                    }
                    i++;
                    System.out.println(String.format("%s %s", numStr, divisors));
                }
            }
        }
    }

    private static class Divisor {
        long number;
        boolean found;
    }

    private static String generateNumberString(int n, int size) {
        StringBuilder binString = new StringBuilder("1");
        String val = Integer.toBinaryString(n);
        StringBuilder pad = new StringBuilder();
        for (int i = val.length(); i < size; i++) {
            pad.append("0");
        }
        binString.append(pad.toString());
        binString.append(val);
        binString.append("1");
        return binString.toString();
    }

    private static Divisor findDivisor(long number) {
        Divisor divisor = new Divisor();
        for (int n = 2; n < number - 1; n++) {
            double d = (double) number / (double) n;
            int i = (int) d;
            double x = d - i;
            if (x == 0.0) {
                divisor.found = true;
                divisor.number = n;
                return divisor;
            }
        }
        return divisor;
    }
}
