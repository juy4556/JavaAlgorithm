package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇_조종하기_2169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] space = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                int num = Integer.parseInt(st.nextToken());
                space[i][j] = num;
                dp[i][j] = num;
            }
        }
        for (int i = 1; i < M + 1; i++) {
            dp[1][i] += dp[1][i - 1];
        }
        for (int i = 2; i < N + 1; i++) {
            int[] fromLeft = dp[i].clone();
            int[] fromRight = dp[i].clone();
            fromLeft[1] += dp[i - 1][1];
            for (int j = 2; j < M + 1; j++) {
                if (dp[i - 1][j] > fromLeft[j - 1]) {
                    fromLeft[j] += dp[i - 1][j];
                } else {
                    fromLeft[j] += fromLeft[j - 1];
                }
            }
            fromRight[M] += dp[i - 1][M];
            for (int j = M - 1; j > 0; j--) {
                if (dp[i - 1][j] > fromRight[j + 1]) {
                    fromRight[j] += dp[i - 1][j];
                } else {
                    fromRight[j] += fromRight[j + 1];
                }
            }
            for (int j = 1; j < M + 1; j++) {
                if (fromRight[j] > fromLeft[j]) {
                    dp[i][j] = fromRight[j];
                } else {
                    dp[i][j] = fromLeft[j];
                }
            }
        }
        System.out.println(dp[N][M]);
        br.close();
    }
}
