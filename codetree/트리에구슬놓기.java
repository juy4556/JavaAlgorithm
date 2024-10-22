package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리에구슬놓기 {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] visited;
    static int[][] dp;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new int[N + 1];
        graph = new ArrayList[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);
        result = Math.min(dp[1][0], dp[1][1]);
        System.out.println(result);
    }

    private static class Node {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    private static void dfs(int node) {
        visited[node] = 1;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int next : graph[node]) {
            if (visited[next] == 1) {
                continue;
            }
            dfs(next);
            dp[node][0] += dp[next][1];
            dp[node][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}
