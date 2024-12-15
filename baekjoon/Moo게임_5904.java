package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Moo게임_5904 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int totalLength = 3;
        int midLength = 3;
        while (N > totalLength) {
            midLength++;
            totalLength = totalLength * 2 + midLength;
        }

        while (true) {
            int left = (totalLength - midLength) / 2;

            if (N <= left) {
                totalLength = left;
                midLength--;
            } else if (N <= left + midLength) {
                N -= left;
                break;
            } else {
                N -= left + midLength;
                totalLength = left;
                midLength--;
            }
        }

        if (N == 1) {
            System.out.println("m");
        } else {
            System.out.println("o");
        }
    }
}
