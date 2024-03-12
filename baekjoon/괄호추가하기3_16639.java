package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호추가하기3_16639 {
    static int N;
    static int[][][] dp;
    static char[] operators;
    static int operatorCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operatorCount = N / 2;
        dp = new int[operatorCount + 1][operatorCount + 1][2];  // 각 범위에 대한 최대값과 최소값
        operators = new char[operatorCount];
        String expression = br.readLine();
        dp[0][0][0] = expression.charAt(0) - '0';
        dp[0][0][1] = expression.charAt(0) - '0';
        for (int i = 1; i < N; i += 2) {
            int index = i / 2;
            operators[index] = expression.charAt(i);
            dp[index + 1][index + 1][0] = expression.charAt(i + 1) - '0';
            dp[index + 1][index + 1][1] = expression.charAt(i + 1) - '0';
        }

        for (int i = 0; i < operatorCount; i++) {
            for (int j = 0; j < operatorCount - i; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                int y = i + j + 1;
                for (int k = j; k < y; k++) {
                    int tempMax = Integer.MIN_VALUE;
                    int tempMin = Integer.MAX_VALUE;
                    if (operators[k] == '+') {
                        tempMax = dp[j][k][0] + dp[k + 1][y][0];
                        tempMin = dp[j][k][1] + dp[k + 1][y][1];
                    } else if (operators[k] == '-') {
                        tempMax = dp[j][k][0] - dp[k + 1][y][1];
                        tempMin = dp[j][k][1] - dp[k + 1][y][0];
                    } else {
                        int frontMax = dp[j][k][0];
                        int frontMin = dp[j][k][1];
                        int backMax = dp[k + 1][y][0];
                        int backMin = dp[k + 1][y][1];
                        int temp = frontMax * backMax;
                        tempMax = Math.max(tempMax, temp);
                        tempMin = Math.min(tempMin, temp);
                        temp = frontMax * backMin;
                        tempMax = Math.max(tempMax, temp);
                        tempMin = Math.min(tempMin, temp);
                        temp = frontMin * backMax;
                        tempMax = Math.max(tempMax, temp);
                        tempMin = Math.min(tempMin, temp);
                        temp = frontMin * backMin;
                        tempMax = Math.max(tempMax, temp);
                        tempMin = Math.min(tempMin, temp);
                    }
                    max = Math.max(max, tempMax);
                    min = Math.min(min, tempMin);
                }
                dp[j][y][0] = max;
                dp[j][y][1] = min;
            }
        }
        System.out.println(dp[0][operatorCount][0]);
    }

}
