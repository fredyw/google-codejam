package gcj2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://code.google.com/codejam/contest/3264486/dashboard#s=p2
 */
public class BathroomStalls {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2017/bathroomstalls/sample.txt");
        // File file = new File("src/main/resources/2017/bathroomstalls/C-small-1-attempt0.in");
        // File file = new File("src/main/resources/2017/bathroomstalls/C-small-1-attempt1.in");
        // File file = new File("src/main/resources/2017/bathroomstalls/C-small-1-attempt2.in");
        // File file = new File("src/main/resources/2017/bathroomstalls/C-small-2-attempt0.in");
        File file = new File("src/main/resources/2017/bathroomstalls/C-large.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                long n = scanner.nextLong();
                long k = scanner.nextLong();
                MinMax minMax = lastStall(n, k);
                System.out.println(String.format("Case #%d: %d %d", t, minMax.max, minMax.min));
            }
        }
    }

    private static class Stall {
        private final long left;
        private final long right;

        public Stall(long left, long right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + ", " + right + ")";
        }
    }

    private static class MinMax {
        private final long min;
        private final long max;

        public MinMax(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }

    private static MinMax lastStall(long n, long k) {
        PriorityQueue<Stall> stalls = new PriorityQueue<>((a, b) -> {
            long diffA = a.right - a.left;
            long diffB = b.right - b.left;
            int cmp = Long.compare(diffB, diffA);
            if (cmp == 0) {
                return Long.compare(a.left, b.left);
            }
            return cmp;
        });
        stalls.add(new Stall(0, n - 1));
        Stall stall = null;
        long mid = 0;
        for (int i = 0; i < k; i++) {
            stall = stalls.remove();
            mid = (stall.left + stall.right) / 2;
            if (stall.left <= mid - 1) {
                stalls.add(new Stall(stall.left, mid - 1));
            }
            if (mid + 1 <= stall.right) {
                stalls.add(new Stall(mid + 1, stall.right));
            }
        }
        long left = mid - stall.left;
        long right = stall.right - mid;
        return new MinMax(Math.min(left, right), Math.max(left, right));
    }
}
