package baekjoon;

import java.io.*;

public class 레벨햄버거 {
    static int N;
    static Long X;
    static long[] hamburger;
    static long[] patty;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        X = Long.parseLong(input[1]);
        hamburger = new long[N + 1];
        patty = new long[N + 1];
        hamburger[0] = 1L;
        patty[0] = 1L;

        for (int i = 1; i <= N; i++) {
            hamburger[i] = hamburger[i - 1] * 2 + 3;
            patty[i] = patty[i - 1] * 2 + 1;
        }

        System.out.println(eat(N, X));
    }

    private static long eat(int n, long x) {
        if (n == 0) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        long eatCount = 0;
        if (x == hamburger[n - 1] + 2) {
            eatCount += patty[n - 1] + 1;
        } else if (x == hamburger[n - 1] + 1) {
            eatCount += patty[n - 1];
        } else if (x < hamburger[n - 1] + 1) {
            eatCount += eat(n - 1, x - 1);
        } else if (x > hamburger[n - 1] + 2) {
            eatCount += patty[n - 1] + 1 + eat(n - 1, x - hamburger[n - 1] - 2);
        }
        return eatCount;
    }
}
