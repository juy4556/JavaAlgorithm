package codetree;

import java.io.*;
import java.util.*;

public class 거스름돈계산하기 {
    static int N, S;
    static Integer[] coins;
    static int[] dp;
    static StringTokenizer st;
    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        coins = new Integer[N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            coins[i] = v;
            count += a;
            S -= v * a;
        }

        dp = new int[S + 1];

        for (int i = 0; i <= S; i++) {
            dp[i] = MAX;
        }

        for (int i = 0; i < N; i++) {
            dp[coins[i]] = 1;
        }

        Arrays.sort(coins, Comparator.naturalOrder());

        for (int i = coins[0]; i <= S; i++) {

            if (dp[i] == MAX) {
                continue;
            }

            for (int j = 0; j < N; j++) {
                if (i + coins[j] > S) {
                    break;
                }
                dp[i + coins[j]] = Math.min(dp[i + coins[j]], dp[i] + 1);
            }
        }


        if (dp[S] == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(dp[S] + count);
        }
    }
}