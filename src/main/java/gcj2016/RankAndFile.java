package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * https://code.google.com/codejam/contest/4304486/dashboard
 */
public class RankAndFile {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2016/rankandfile/sample.txt");
        // File file = new File("src/main/resources/2016/rankandfile/B-small-attempt0.in");
        // File file = new File("src/main/resources/2016/rankandfile/B-small-practice.in");
        // File file = new File("src/main/resources/2016/rankandfile/B-large.in");
        File file = new File("src/main/resources/2016/rankandfile/B-large-practice.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                int n = Integer.parseInt(br.readLine());
                int size = (n * 2) - 1;
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < size; i++) {
                    List<Integer> list = toList(br.readLine());
                    for (int x : list) {
                        if (!map.containsKey(x)) {
                            map.put(x, 1);
                        } else {
                            map.put(x, map.get(x) + 1);
                        }
                    }
                }
                List<Integer> missingList = new ArrayList<>();
                map.forEach((key, count) -> {
                    if (count % 2 != 0) {
                        missingList.add(key);
                    }
                });
                Collections.sort(missingList);
                StringJoiner joiner = new StringJoiner(" ");
                for (int i : missingList) {
                    joiner.add(Integer.toString(i));
                }
                System.out.println(String.format("Case #%d: %s", t, joiner.toString()));
            }
        }
    }

    private static List<Integer> toList(String str) {
        List<Integer> list = new ArrayList<>();
        String[] pieces = str.split("\\s+");
        for (String piece : pieces) {
            list.add(Integer.parseInt(piece));
        }
        return list;
    }
}
