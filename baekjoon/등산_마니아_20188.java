package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 등산_마니아_20188 {
    static int N;
    static List<Integer>[] trees;
    static int[] subTreeCount, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        trees = new ArrayList[N + 1];
        long total = (long) N * (N - 1) / 2;
        long result = 0;
        for (int i = 0; i <= N; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            trees[a].add(b);
            trees[b].add(a);
        }
        subTreeCount = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            subTreeCount[i] = 1;
        }
        visited = new int[N + 1];
        visited[1] = 1;
        dfs(1);
        for (int i = 2; i <= N; i++) {
            long rest = N - subTreeCount[i];
            result += total - ((rest * (rest - 1)) / 2);
        }
        System.out.println(result);
    }

    private static int dfs(int root) {
        for (int node : trees[root]) {
            if (visited[node] == 0) {
                visited[node] = 1;
                subTreeCount[root] += dfs(node);
            }
        }
        return subTreeCount[root];
    }
}
