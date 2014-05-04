package gcj2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheRepeater {
    private static class StringInfo {
        public final List<CharInfo> charInfoList = new ArrayList<>();

        @Override
        public String toString() {
            return charInfoList.toString();
        }
    }
    
    private static class CharInfo {
        public final char c;
        public final int n;
        
        public CharInfo(char c, int n) {
            this.c = c;
            this.n = n;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(").append(c).append(",").append(n).append(")");
            return builder.toString();
        }
    }
    
    private static StringInfo buildStringInfo(String s) {
        StringInfo si = new StringInfo();
        char c = s.charAt(0);
        int n = 1;
        for (int i = 1; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                n++;
            } else {
                si.charInfoList.add(new CharInfo(c, n));
                c = s.charAt(i);
                n = 1;
            }
        }
        si.charInfoList.add(new CharInfo(c, n));
        return si;
    }
    
    private static int minimum(List<Integer> integers) {
        int result = -1;
        Set<Integer> nonDuplicateIntegers = new HashSet<>(integers);
        for (int i : nonDuplicateIntegers) {
            int min = 0;
            for (int j : integers) {
                if (j == i) {
                    continue;
                }
                min += Math.abs(i - j);
            }
            
            if (result == -1 || min < result) {
                result = min;
            }
        }
        return result;
    }
    
    private static int solution(List<String> strings) {
        int result = 0;
        List<StringInfo> stringInfoList = new ArrayList<StringInfo>();
        StringInfo si1 = buildStringInfo(strings.get(0));
        stringInfoList.add(si1);
        for (int i = 1; i < strings.size(); i++) {
            StringInfo si2 = buildStringInfo(strings.get(i));
            if (si1.charInfoList.size() != si2.charInfoList.size()) {
                return -1;
            }
            for (int j = 0; j < si1.charInfoList.size(); j++) {
                if (si1.charInfoList.get(j).c != si2.charInfoList.get(j).c) {
                    return -1;
                }
            }
            stringInfoList.add(si2);
        }
        
        int size = stringInfoList.get(0).charInfoList.size();
        for (int i = 0; i < size; i++) {
            List<Integer> integers = new ArrayList<>();
            for (StringInfo si : stringInfoList) {
                CharInfo ci = si.charInfoList.get(i);
                integers.add(ci.n);
            }
            result += minimum(integers);
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int t = 1; t <= testCases; t++) {
                int n = Integer.parseInt(br.readLine());
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    strings.add(br.readLine());
                }
                
                int s = solution(strings);
                if (s < 0) {
                    System.out.println(String.format("Case #%d: Fegla Won", t));
                } else {
                    System.out.println(String.format("Case #%d: %d", t, s));
                }
            }
        }
    }
}
