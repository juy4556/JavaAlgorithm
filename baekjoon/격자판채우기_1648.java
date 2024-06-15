package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 격자판채우기_1648 {
    static int N, M;
    static StringTokenizer st;
    static int[][] dp;
    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N * M][1 << M]; // N번째 도미노에서 N ~ (N + M - 1) 번째 도미노의 상태를 나타내는 비트마스크
        if (((N * M) & 1) == 1) {
            System.out.println(0);
            return;
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int num, int status) {
        if (num == N * M) {
            return status == 0 ? 1 : 0;
        }
        if (dp[num][status] > 0) {
            return dp[num][status];
        }
        if ((status & 1) == 1) {
            dp[num][status] += dfs(num + 1, status >> 1);
        } else {
            if ((status & 2) == 0 && num % M < M - 1) { // 가로(1*2)로 놓는 경우
                dp[num][status] += dfs(num + 2, status >> 2);
            }
            dp[num][status] += dfs(num + 1, (status >> 1) | (1 << (M - 1))); // 세로(2*1)로 놓는 경우
        }
        return dp[num][status] % MOD;
    }
}
