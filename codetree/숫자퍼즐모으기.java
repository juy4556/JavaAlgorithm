package codetree;

import java.io.*;
import java.util.*;

public class 숫자퍼즐모으기 {
    static int N, M;
    static StringTokenizer st;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Set<Integer>> list = new ArrayList<>();
        result = N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < M; j++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            list.add(set);
        }

        dfs(list, 0, new HashSet<>(), 0);
        System.out.println(result);
    }

    private static void dfs(List<Set<Integer>> list, int begin, Set<Integer> set, int count) {
        if (count > result) {
            return;
        }
        if (set.size() == 10) {
            result = Math.min(result, count);
            return;
        }

        for (int i = begin; i < N; i++) {

            Set<Integer> tempSet = new HashSet<>(set);
            tempSet.addAll(list.get(i));
            dfs(list, i + 1, tempSet, count + 1);
        }
    }
}