package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단수_1562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        int mod = 1000000000;
        int[][][] dp = new int[N + 1][10][1 << 10]; // dp[i][j][k]: i자리 계단수 중에서 j로 끝나고 k를 모두 포함하는 계단수의 개수
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j > 0) {
                        dp[i][j][bit] += dp[i - 1][j - 1][k];
                    }
                    if (j < 9) {
                        dp[i][j][bit] += dp[i - 1][j + 1][k];
                    }
                    dp[i][j][bit] %= mod;
                }
            }
        }
        for (int i = 0; i <= 9; i++) {
            result += dp[N][i][1023];
            result %= mod;
        }
        System.out.println(result);
    }
}
