package gcj2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RankAndFile {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/2016/rankandfile/sample.txt");
        // File file = new File("src/main/resources/2016/rankandfile/B-small-attempt0.in");
        // File file = new File("src/main/resources/2016/rankandfile/B-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                int n = Integer.parseInt(br.readLine());
                TreeSet<List<Integer>> set = new TreeSet<>((a, b) -> {
                    for (int i = 0; i < a.size(); i++) {
                        if (a.get(i) < b.get(i)) {
                            return a.get(i).compareTo(b.get(i));
                        } else if (a.get(i) > b.get(i)) {
                            return a.get(i).compareTo(b.get(i));
                        }
                    }
                    return 0;
                });
                TreeMap<List<Integer>, Integer> map = new TreeMap<>((a, b) -> {
                    for (int i = 0; i < a.size(); i++) {
                        if (a.get(i) < b.get(i)) {
                            return a.get(i).compareTo(b.get(i));
                        } else if (a.get(i) > b.get(i)) {
                            return a.get(i).compareTo(b.get(i));
                        }
                    }
                    return 0;
                });
                int size = (n * 2) - 1;
                for (int i = 0; i < size; i++) {
                    List<Integer> list = toList(br.readLine());
                    if (map.containsKey(list)) {
                        map.put(list, map.get(list) + 1);
                    } else {
                        map.put(list, 1);
                    }
                    set.add(list);
                }
                String missingList = missingList(set, map, n);
                System.out.println(String.format("Case #%d: %s", t, missingList));
            }
        }
    }

    private static String missingList(TreeSet<List<Integer>> set,
                                      TreeMap<List<Integer>, Integer> map,
                                      int n) {
        List<Integer> previous = null;
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        outer:
        while (i < n) {
            List<Integer> current = set.pollFirst();
            if (current == null) {
                return "";
            }
            if (previous == null) {
                previous = current;
                result.add(current);
            } else {
                for (int j = 0; j < previous.size(); j++) {
                    int p = previous.get(j);
                    int c = current.get(j);
                    if (p >= c) {
                        continue outer;
                    }
                }
                previous = current;
                result.add(current);
            }
            i++;
        }
        // by row
        for (int row = 0; row < n; row++) {
            List<Integer> key = result.get(row);
            Integer c = map.get(key);
            if (c == null) {
                return key.stream()
                    .map(in -> in.toString())
                    .collect(Collectors.joining(" "));
            }
            int count = c - 1;
            if (count == 0) {
                map.remove(key);
            } else {
                map.put(key, count);
            }
        }
        // by col
        for (int col = 0; col < n; col++) {
            List<Integer> key = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                Integer val = result.get(row).get(col);
                key.add(val);
            }
            Integer c = map.get(key);
            if (c == null) {
                return key.stream()
                    .map(in -> in.toString())
                    .collect(Collectors.joining(" "));
            }
            int count = c - 1;
            if (count == 0) {
                map.remove(key);
            } else {
                map.put(key, count);
            }
        }
        return "";
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
