package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 특이한LIS {
    static int N, K;
    static int[] num;
    static int[][] dp;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = new int[N];
        dp = new int[N][K + 1];
        int result = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            dp[i][1] = 1;
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (num[i] < num[j]) {
                    continue;
                }
                if (num[i] > num[j]) {
                    for (int k = 1; k <= K; k++) {
                        dp[i][1] = Math.max(dp[i][1], dp[j][k] + 1);
                    }
                    continue;
                }
                // num[i] == num[j]
                for (int k = 2; k <= K; k++) {
                    dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + 1);
                }

            }

            for (int j = 1; j <= K; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        System.out.println(result);
    }
}
