package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주식_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int days = Integer.parseInt(br.readLine());
            int[] prices = new int[days];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < days; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[days][3];
            dp[0] = new int[]{1, 0, 0};
            dp[1] = new int[]{2, prices[1], 1};
            int maxPrice = 0;
            long total = 0;
            for (int i = days - 1; i >= 0; i--) {
                if (prices[i] > maxPrice) {
                    maxPrice = prices[i];
                }
                total += maxPrice - prices[i];
            }
            sb.append(total).append('\n');
        }
        System.out.println(sb);
    }
}
