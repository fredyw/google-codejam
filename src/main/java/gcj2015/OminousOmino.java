package gcj2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OminousOmino {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int n = 1; n <= testCases; n++) {
                String[] splitLine = br.readLine().split(" ");
                int x = Integer.parseInt(splitLine[0]);
                int r = Integer.parseInt(splitLine[1]);
                int c = Integer.parseInt(splitLine[2]);
                int total = r * c;
                String winner = "";
                if (x == total) {
                    winner = "RICHARD";
                } else {
                    if (total % x == 0) {
                        winner = "GABRIEL";
                    } else {
                        winner = "RICHARD";
                    }
                }
                System.out.println(String.format("Case #%d: %s", n, winner));
            }
        }
    }
}
