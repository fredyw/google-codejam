package gcj2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://code.google.com/codejam/contest/2974486/dashboard
 */
public class MinesweeperMaster {
    private static void solution(int r, int c, int m) {
        // TODO:
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int n = 0; n < testCases; n++) {
                String[] line = br.readLine().split(" ");
                int r = Integer.parseInt(line[0]);
                int c = Integer.parseInt(line[1]);
                int m = Integer.parseInt(line[2]);
                solution(r, c, m);
            }
        }
    }
}
