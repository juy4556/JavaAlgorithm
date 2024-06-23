package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두배보다커지는수열 {

    public static class Main {
        static int n, m;
        static int MOD = 1_000_000_007;
        static int[][] dp;
        static StringTokenizer st;
        static int result = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            dp = new int[m + 1][n + 1];  // 끝 숫자 m일 때, n개 수열 몇 개

            for (int i = 1; i <= m; i++) {
                dp[i][1] = 1;
            }

            for (int i = 2; i <= m; i++) {
                for (int j = 2; j <= n; j++) {
                    for (int k = i / 2; k >= 1; k--) {
                        dp[i][j] += dp[k][j - 1];
                        dp[i][j] %= MOD;
                    }
                }
            }

            for (int i = 1; i <= m; i++) {
                result += dp[i][n];
                result %= MOD;
            }

            System.out.println(result);
        }
    }
}
