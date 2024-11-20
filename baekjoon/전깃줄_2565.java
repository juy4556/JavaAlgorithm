package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 전깃줄_2565 {
    static int lineCount;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lineCount = Integer.parseInt(br.readLine());
        int[] lines = new int[501];
        int[] dp = new int[501];

        for (int i = 0; i < lineCount; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[a] = b;
        }

        int max = 0;

        for (int i = 500; i >= 1; i--) {
            if (lines[i] == 0) continue;
            dp[i] = 1;
            for (int j = i + 1; j <= 500; j++) {
                if (lines[j] == 0) continue;
                if (lines[i] < lines[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(lineCount - max);
    }
}
