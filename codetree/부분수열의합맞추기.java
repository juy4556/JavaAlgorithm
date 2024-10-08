package codetree;

import java.io.*;
import java.util.*;

public class 부분수열의합맞추기 {
    static int n, k;
    static int[] A;
    static int[][] dp;
    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        A = new int[n];
        dp = new int[n][k + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = MAX;
            }
        }
        if (A[0] <= k) {
            dp[0][A[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (A[i] <= k) {
                dp[i][A[i]] = 1;
            }
            for (int j = 0; j <= k; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                if (j - A[i] >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - A[i]] + 1);
                }
            }
        }

        if (dp[n - 1][k] == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(dp[n - 1][k]);
        }
    }
}