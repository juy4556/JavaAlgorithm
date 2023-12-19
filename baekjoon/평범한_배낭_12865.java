package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 평범한_배낭_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];
        ArrayList<int[]> arrayList = new ArrayList<>(N);
        int W, V;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            arrayList.add(new int[]{W, V});
        }
        for (int i = 1; i < N + 1; i++) {
            W = arrayList.get(i - 1)[0];
            V = arrayList.get(i - 1)[1];
            for (int j = 1; j < K + 1; j++) {
                if (j >= W) {
                    dp[i][j] = Math.max(dp[i - 1][j - W] + V, dp[i - 1][j]);
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[N][K]);
    }
}
