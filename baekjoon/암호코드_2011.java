package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 암호코드_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password = br.readLine();
        if (password.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        int[] dp = new int[password.length()];
        dp[0] = 1;
        if (password.length() > 1) {
            int num = Integer.parseInt(password.substring(0, 2));
            if (password.charAt(1) != '0') {
                dp[1]++;
            }
            if (num >= 10 && num <= 26) {
                dp[1]++;
            }

            for (int i = 2; i < password.length(); i++) {
                if (password.charAt(i) != '0') {
                    dp[i] += dp[i - 1];
                }
                num = Integer.parseInt(password.substring(i - 1, i + 1));
                if (num >= 10 && num <= 26) {
                    dp[i] += dp[i - 2];
                }
                dp[i] %= 1000000;
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[password.length() - 1]);
    }
}
