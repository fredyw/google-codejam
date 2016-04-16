package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * https://code.google.com/codejam/contest/6254486/dashboard
 */
public class Fractiles {
    public static void main(String[] args) throws Exception {
        // File file = new File(args[0]);
        File file = new File("src/main/resources/2016/fractiles/sample.txt");
        // File file = new File("src/main/resources/2016/fractiles/D-small-attempt0.in");
        // File file = new File("src/main/resources/2016/fractiles/B-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int n = 1; n <= testCases; n++) {
                String[] parts = br.readLine().split("\\s+");
                int k = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                int s = Integer.parseInt(parts[2]);
                List<String> originalArtworks = new ArrayList<>();
                generateOriginalArtworks(0, k, "", originalArtworks);
                originalArtworks.forEach(System.out::println);
                List<String> finalArtworks = generateFinalArtworks(c, originalArtworks);
                int colSize = (int) Math.pow(k, c);
                String tiles = getTiles(s, colSize, finalArtworks);
                System.out.println(String.format("Case #%d: %s", n, tiles));
            }
        }
    }

    private static String getTiles(int s, int colSize,  List<String> artworks) {
        Set<Integer> processedRows = new HashSet<>();
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 0; i < s; i++) {
            int maxG = 0;
            int maxCol = -1;
            Set<Integer> maxRows = new HashSet<>();
            for (int col = 0; col < colSize; col++) {
                int numG = 0;
                Set<Integer> rows = new HashSet<>();
                for (int row = 0; row < artworks.size() - 1; row++) {
                    if (processedRows.contains(row)) {
                        continue;
                    }
                    String artwork = artworks.get(row);
                    char ch = artwork.charAt(col);
                    if (ch == 'G') {
                        numG++;
                        rows.add(row);
                    }
                }
                if (maxG < numG) {
                    maxG = numG;
                    maxRows = rows;
                    maxCol = col;
                }
            }
            processedRows.addAll(maxRows);
            joiner.add(Integer.toString(maxCol + 1));
            if (processedRows.size() == artworks.size() - 1) {
                return joiner.toString();
            }
        }
        return "IMPOSSIBLE";
    }

    private static List<String> generateFinalArtworks(int c, List<String> originalArtworks) {
        List<String> finalArtworks = new ArrayList<>();
        for (String originalArtwork : originalArtworks) {
            int i = 1;
            StringBuilder finalArtwork = new StringBuilder();
            while (i < c) {
                for (char ch : originalArtwork.toCharArray()) {
                    if (ch == 'L') {
                        finalArtwork.append(originalArtwork);
                    } else if (ch == 'G') {
                        for (int j = 0; j < originalArtwork.length(); j++) {
                            finalArtwork.append('G');
                        }
                    }
                }
                i++;
            }
            if (finalArtwork.length() == 0) {
                finalArtwork.append(originalArtwork);
            }
            finalArtworks.add(finalArtwork.toString());
        }
        return finalArtworks;
    }

    private static void generateOriginalArtworks(int n, int k, String accu, List<String> artworks) {
        if (n == k) {
            artworks.add(accu);
            return;
        }
        String newAccu = accu;
        newAccu += 'G';
        generateOriginalArtworks(n + 1, k, newAccu, artworks);
        newAccu = accu;
        newAccu += 'L';
        generateOriginalArtworks(n + 1, k, newAccu, artworks);
    }
}
