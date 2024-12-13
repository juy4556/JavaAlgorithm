package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MessiGimossi_17297 {
    static int M;
    static List<Integer> dp;
    static final String MG = "Messi Gimossi";
    static final String MMG = "Messi Messi Gimossi";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        dp = new ArrayList<>();
        dp.add(5);
        dp.add(13);
        int a = 5;
        int b = 13;
        int c = a + b + 1;
        if (M == 6) {
            System.out.println(MMG);
            return;
        }
        if (M <= 13) {
            System.out.println(MG.charAt(M - 1));
            return;
        }

        while (c < M) {
            c = a + b + 1;
            dp.add(c);
            a = b;
            b = c;
        }

        divide(M, dp.size());
    }

    private static void divide(int length, int index) {
        if (index == 0) {
            System.out.println("Messi".charAt(length - 1));
            return;
        }
        if (index == 1) {
            if (length == 6) {
                System.out.println(MMG);
                return;
            }
            System.out.println(MG.charAt(length - 1));
            return;
        }
        if (length <= dp.get(index - 1)) {
            divide(length, index - 1);
        } else if (length == dp.get(index - 1) + 1) {
            System.out.println(MMG);
        } else {
            divide(length - dp.get(index - 1) - 1, index - 2);
        }

    }
}
