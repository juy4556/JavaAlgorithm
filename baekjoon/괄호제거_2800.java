package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 괄호제거_2800 {
    static List<int[]> pairs;
    static String str;
    static List<String> result = new ArrayList<>();
    static int[] excluded;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        List<Integer> lefts = new LinkedList<>();
        pairs = new ArrayList<>();
        excluded = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                lefts.add(i);
            } else if (str.charAt(i) == ')') {
                int leftIdx = lefts.remove(lefts.size() - 1);
                pairs.add(new int[]{leftIdx, i});
            }
        }

        dfs(0, 0);

        Set<String> set = new HashSet<>(result);
        result = new ArrayList<>(set);
        result.sort(Comparator.naturalOrder());

        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void dfs(int start, int mask) {
        if (mask > 0) {
            StringBuilder sb = new StringBuilder();
            excluded = new int[str.length()];
            for (int i = 0; i < pairs.size(); i++) {
                int[] pair = pairs.get(i);
                int left = pair[0];
                int right = pair[1];
                if ((mask & (1 << i)) > 0) {
                    excluded[left] = 1;
                    excluded[right] = 1;
                }
            }
            for (int i = 0; i < str.length(); i++) {
                if (excluded[i] == 0) {
                    sb.append(str.charAt(i));
                }
            }

            result.add(sb.toString());
        }

        for (int i = start; i < pairs.size(); i++) {
            dfs(i + 1, mask | (1 << i));
        }
    }
}
