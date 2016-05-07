package gcj2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://code.google.com/codejam/contest/2974486/dashboard
 */
public class CookieClickerAlpha {
    private static double getTime(double c, double f, double x) {
        double totalTime = 0.0;
        double cookie = 2.0;
        while (true) {
            double farmTime = c / cookie;
            double time = x / cookie;
            if (time + totalTime < farmTime + (x / (cookie + f)) + totalTime) {
                totalTime += time;
                break;
            } else {
                totalTime += farmTime;
                cookie += f;
            }
        }
        return totalTime;
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= testCases; i++) {
                String[] line = br.readLine().split(" ");
                double c = Double.parseDouble(line[0]);
                double f = Double.parseDouble(line[1]);
                double x = Double.parseDouble(line[2]);
                System.out.println(String.format("Case #%d: %.7f", i, getTime(c, f, x)));
            }
        }
    }
}
