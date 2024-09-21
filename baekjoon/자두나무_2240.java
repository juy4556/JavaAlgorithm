package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 자두나무_2240 {

    private static int T, W;
    private static int[] fall;
    private static int[][][] dp;
    private static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        T = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        fall = new int[T];
        dp = new int[T + 1][W + 1][2];

        for (int i = 0; i < T; i++) {
            fall[i] = Integer.parseInt(br.readLine());
        }

        // 자두는 처음 1번 자두나무 아래에 위치
        if (fall[0] == 1) {
            dp[1][0][0] = 1;
        } else {
            dp[1][1][1] = 1;
        }

        for (int i = 2; i <= T; i++) {
            for (int j = 0; j <= Math.min(i - 1, W); j++) {
                if (j == 0) {
                    if (fall[i - 1] == 1) {
                        dp[i][j][0] = dp[i - 1][j][0] + 1;
                        continue;
                    }
                    dp[i][j][0] = dp[i - 1][j][0];
                    continue;
                }

                if (fall[i - 1] == 1) {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0] + 1, dp[i - 1][j - 1][1] + 1);
                    dp[i][j][1] = Math.max(dp[i - 1][j - 1][0], dp[i - 1][j][1]);
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1] + 1, dp[i - 1][j - 1][0] + 1);
                }
            }
        }

        for (int i = 0; i <= Math.min(T, W); i++) {
            result = Math.max(result, Math.max(dp[T][i][0], dp[T][i][1]));
        }

        System.out.println(result);
    }
}
