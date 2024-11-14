package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사전_1256 {
    static int N, M, K;
    static int[][] dp;
    static final int MAX = 1_000_000_000;
    static final int INF = 1_000_000_001;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        dp = new int[N + 1][M + 1];
//        dp[1][0] = 1;
//        dp[0][1] = 1;

        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= M; j++) {
                dp[0][j] = 1;
                if (dp[i - 1][j] + dp[i][j - 1] > MAX) {
                    dp[i][j] = INF;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        dfs(N, M, K);
        if (sb.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(sb.toString());
        }
    }

    private static void dfs(int n, int m, int k) {
        if (dp[n][m] < k) {
            return;
        }
        if (n == 0) {
            sb.append("z".repeat(m));
        } else if (m == 0) {
            sb.append("a".repeat(n));
        } else {
            int left = dp[n - 1][m];
            if (1 <= k && k <= left) {
                sb.append('a');
                dfs(n - 1, m, k);
            } else if (k > left) {
                sb.append('z');
                dfs(n, m - 1, k - left);
            }
        }
    }
}
