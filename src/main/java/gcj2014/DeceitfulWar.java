package gcj2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://code.google.com/codejam/contest/2974486/dashboard
 */
public class DeceitfulWar {
    private static int getNormalWarScore(int n, List<Double> naomi, List<Double> ken) {
        Set<Integer> naomiMarked = new HashSet<Integer>();
        Set<Integer> kenMarked = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < naomi.size(); j++) {
                if (naomiMarked.contains(j)) {
                    continue;
                }
                double naomiWeight = naomi.get(j);
                for (int k = 0; k < ken.size(); k++) {
                    if (kenMarked.contains(k)) {
                        continue;
                    }
                    double kenWeight = ken.get(k);
                    if (kenWeight > naomiWeight) {
                        naomiMarked.add(j);
                        kenMarked.add(k);
                        break;
                    }
                }
            }
        }
        return naomi.size() - naomiMarked.size();
    }

    private static int getDeceitfulWarScore(int n, List<Double> naomi, List<Double> ken) {
        Set<Integer> naomiMarked = new HashSet<Integer>();
        Set<Integer> kenMarked = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < ken.size(); j++) {
                if (kenMarked.contains(j)) {
                    continue;
                }
                double kenWeight = ken.get(j);
                for (int k = 0; k < naomi.size(); k++) {
                    if (naomiMarked.contains(k)) {
                        continue;
                    }
                    double naomiWeight = naomi.get(k);
                    if (kenWeight < naomiWeight) {
                        naomiMarked.add(k);
                        kenMarked.add(j);
                        break;
                    }
                }
            }
        }
        return naomiMarked.size();
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int n = 1; n <= testCases; n++) {
                int nBlocks = Integer.parseInt(br.readLine());
                List<Double> naomi = new ArrayList<>();
                for (String s : br.readLine().split(" ")) {
                    naomi.add(Double.parseDouble(s));
                }
                List<Double> ken = new ArrayList<>();
                for (String s : br.readLine().split(" ")) {
                    ken.add(Double.parseDouble(s));
                }
                Collections.sort(naomi);
                Collections.sort(ken);
                int deceitful = getDeceitfulWarScore(nBlocks, naomi, ken);
                int normal = getNormalWarScore(nBlocks, naomi, ken);
                System.out.println(String.format("Case #%d: %d %d", n, deceitful, normal));
            }
        }
    }
}
