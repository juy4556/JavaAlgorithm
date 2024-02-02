package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회2_10971 {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int MAX = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   // 도시의 수
        W = new int[N][N];  // 비용
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 1));  // 0번 도시부터 시작, 0번 도시는 방문했다고 표시
    }

    public static int dfs(int now, int bits) {
        if (bits == (1 << N) - 1) {
            if (W[now][0] == 0) return MAX;
            return W[now][0];
        }
        if (dp[now][bits] != -1) {
            return dp[now][bits];
        }
        dp[now][bits] = MAX;
        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) == 0 && W[now][i] != 0) {
                dp[now][bits] = Math.min(dp[now][bits], W[now][i] + dfs(i, bits | (1 << i)));
            }
        }
        return dp[now][bits];
    }
}
