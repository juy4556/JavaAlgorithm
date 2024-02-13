package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 극장_좌석_2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 좌석의 개수
        int M = Integer.parseInt(br.readLine()); // 고정석의 개수
        int[] fixed = new int[M];
        for (int i = 0; i < M; i++) {
            fixed[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int result = 1;
        int prev = 0;
        for (int i = 0; i < M; i++) {
            result *= dp[fixed[i] - prev - 1];
            prev = fixed[i];
        }
        result *= dp[N - prev];
        System.out.println(result);
    }
}
