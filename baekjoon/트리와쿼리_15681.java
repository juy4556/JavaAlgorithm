package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리와쿼리_15681 {
    static int N, R, Q;
    static ArrayList<Integer>[] edges;
    static int[] dp;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        visited = new int[N + 1];
        edges = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[u].add(v);
            edges[v].add(u);
        }

        dfs(R);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(dp[q] + "\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int cur) {
        if (visited[cur] > 0) {
            return dp[cur];
        }
        visited[cur] = 1;
        int count = 1;
        for (int node : edges[cur]) {
            if (visited[node] == 0) {
                dfs(node);
                count += dp[node];
            }
        }
        dp[cur] = count;
        return dp[cur];
    }
}

