package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 할일정하기1_1311 {
    static int N;
    static StringTokenizer st;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][1 << N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dp[0][1 << i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                for (int k = 0; k < (1 << N); k++) {
                    if (dp[i - 1][k] == 0) {
                        continue;
                    }
                    int bits = k | (1 << j);
                    if (dp[i][bits] == 0) {
                        dp[i][bits] = dp[i - 1][k] + cost;
                    } else {
                        dp[i][bits] = Math.min(dp[i][bits], dp[i - 1][k] + cost);
                    }
                }
            }
        }

        System.out.println(dp[N - 1][(1 << N) - 1]);
    }
}
