package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class CountingSheep {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        // File file = new File("src/main/resources/2016/countingsheep/sample.txt");
        // File file = new File("src/main/resources/2016/countingsheep/A-small-attempt0.in");
        // File file = new File("src/main/resources/2016/countingsheep/A-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int n = 1; n <= testCases; n++) {
                String line = br.readLine();
                long number = Long.parseLong(line);
                if (number == 0) {
                    System.out.println(String.format("Case #%d: %s", n, "INSOMNIA"));
                } else {
                    int i = 1;
                    Set<Character> set = new HashSet<>();
                    long lastNumber = 0;
                    while (set.size() < 10) {
                        lastNumber = number * i;
                        String newLine = Long.toString(lastNumber);
                        for (char c : newLine.toCharArray()) {
                            set.add(c);
                        }
                        i++;
                    }
                    System.out.println(String.format("Case #%d: %s", n, lastNumber));
                }
            }
        }
    }
}
