package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 우수마을_1949 {
    static int N;
    static int[] villeges;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        villeges = new int[N + 1];
        graph = new ArrayList[N + 1];
        visited = new int[N + 1];
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            villeges[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur) {
        List<Integer> childs = new ArrayList<>();
        visited[cur] = 1;
        for (int n : graph[cur]) {
            if (visited[n] == 0) {
                childs.add(n);
                dfs(n);
            }
        }
        dp[cur][1] = villeges[cur];
        for (int n : childs) {
            dp[cur][0] += Math.max(dp[n][0], dp[n][1]);
            dp[cur][1] += dp[n][0];
        }
    }
}
