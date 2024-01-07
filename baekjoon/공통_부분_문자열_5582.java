package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공통_부분_문자열_5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        int max = 0;

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        System.out.println(max);
    }
}
