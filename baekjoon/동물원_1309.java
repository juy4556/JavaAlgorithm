package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동물원_1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int MOD = 9901;
        int[][] dp = new int[N + 1][3];
        dp[0][0] = 1; // 0열에 사자가 없는 경우
        dp[0][1] = 1; // 1열에 사자가 있는 경우
        dp[0][2] = 1; // 2열에 사자가 있는 경우
        for (int i = 1; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }
        System.out.println((dp[N][0]) % MOD);
    }
}
