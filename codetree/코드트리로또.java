package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 코드트리로또 {
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dfs(new ArrayList<>(), new HashSet<>(), 1);
        System.out.println(sb);
    }

    private static void dfs(List<Integer> list, Set<Integer> set, int begin) {
        if (list.size() == M) {
            for (int num : list) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = begin; i <= N; i++) {
            if (set.contains(i)) continue;
            list.add(i);
            set.add(i);
            dfs(list, set, i + 1);
            set.remove(i);
            list.remove(list.size() - 1);
        }
    }
}
