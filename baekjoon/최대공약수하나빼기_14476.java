package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 최대공약수하나빼기_14476 {
    static int N;
    static int[] numbers;
    static int[] leftGcd, rightGcd;
    static int result = -1;
    static int maxIndex = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        leftGcd = new int[N + 1];
        rightGcd = new int[N + 1];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        for (int i = 1; i <= N; i++) {
            leftGcd[i] = getGcd(leftGcd[i - 1], numbers[i - 1]);
        }

        for (int i = N - 1; i >= 0; i--) {
            rightGcd[i] = getGcd(rightGcd[i + 1], numbers[i]);
        }

        for (int i = 0; i < N; i++) {
            int gcd = getGcd(leftGcd[i], rightGcd[i + 1]);
            if (numbers[i] % gcd == 0) {
                continue;
            }
            if (gcd > result) {
                result = gcd;
                maxIndex = i;
            }
        }

        if (result == -1) {
            System.out.println(result);
        } else {
            System.out.println(result + " " + numbers[maxIndex]);
        }

    }

    // 최대 공약수 구하기
    private static int getGcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGcd(b, a % b);
    }
}
