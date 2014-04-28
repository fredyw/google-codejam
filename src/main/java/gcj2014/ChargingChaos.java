package gcj2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class ChargingChaos {
    private static String[] sort(String[] str, int length) {
        int[] array = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            array[i] = Integer.parseInt(str[i], 2);
        }
        Arrays.sort(array);
        String[] sortedStr = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            sortedStr[i] = toBinaryString(i, length);
        }
        return sortedStr;
    }
    
    private static String toBinaryString(int i, int length) {
        String s = Integer.toBinaryString(i);
        if (s.length() == length) {
            return s;
        }
        String zeroes = "";
        for (int n = s.length(); n < length; n++) {
            zeroes += "0";
        }
        return zeroes + s;
    }

    private static int solution(String[] initial, String[] expected, int n, int l) {
        // TODO: 
        return -1;
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                String[] line = br.readLine().split(" ");
                int n = Integer.parseInt(line[0]);
                int l = Integer.parseInt(line[1]);
                String[] initial = br.readLine().split(" ");
                String[] expected = br.readLine().split(" ");
                
                solution(initial, expected, n, l);
            }
        }
    }
}
