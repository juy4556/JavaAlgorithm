package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 함께블록쌓기_18427 {
    static int N;
    static int M;
    static int H;
    static ArrayList<Integer>[] blocks;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        blocks = new ArrayList[N];
        dp = new int[N + 1][H + 1];
        dp[0][0] = 1;  // 블록 사용하지 않는 경우
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            blocks[i] = new ArrayList<>();
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1].clone();
            for (int block : blocks[i - 1]) {
                for (int j = block; j <= H; j++) {
                    dp[i][j] += dp[i - 1][j - block];
                    dp[i][j] %= 10007;
                }
            }
        }
        System.out.println(dp[N][H] % 10007);
    }
}
