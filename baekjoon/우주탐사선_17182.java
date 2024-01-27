package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 우주탐사선_17182 {
    static int N, K;
    static int[][] time;  // time[i][j]: i번 행성에서 j번 행성으로 가는데 걸리는 시간
    static int[][] dp;  // dp[i][j]: i번 행성을 방문하고 j번 행성들을 모두 포함하는 최소 시간
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer((br.readLine()));
            for (int j = 0; j < N; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < N; i++) {
            dp[i][(1 << i) - 1] = 0;
        }
        floydWarshall(time);
        result = Math.min(result, dfs(K, 1 << K));
        System.out.println(result);
    }

    public static void floydWarshall(int[][] time) {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
                }
            }
        }
    }

    public static int dfs(int cur, int visited) {
        if (visited == (1 << N) - 1) {
            return 0;
        }
        if (dp[cur][visited] != Integer.MAX_VALUE) {
            return dp[cur][visited];
        }
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0) {  // not visited
                dp[cur][visited] = Math.min(dp[cur][visited], time[cur][i] + dfs(i, visited | (1 << i)));
            }
        }
        return dp[cur][visited];
    }
}
