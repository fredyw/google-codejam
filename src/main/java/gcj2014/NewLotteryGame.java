package gcj2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class NewLotteryGame {
    private static int solution(int a, int b, int k) {
        int count = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if ((i & j) < k) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                String[] line = br.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int b = Integer.parseInt(line[1]);
                int k = Integer.parseInt(line[2]);
                
                System.out.println("Case #" + t + ": " + solution(a, b, k));
            }
        }
    }
}
