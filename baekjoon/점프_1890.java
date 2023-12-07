package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 점프_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] space = new int[N][N];
        long[][] dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1L;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int jump = space[i][j];
                if (jump == 0) continue;
                if (i + jump < N) {
                    dp[i + jump][j] += dp[i][j];
                }
                if (j + jump < N) {
                    dp[i][j + jump] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}
