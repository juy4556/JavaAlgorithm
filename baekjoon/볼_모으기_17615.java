package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 볼_모으기_17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(br.readLine());
        int r = 0;
        int b = 0;
        int result = 1;
        char last = sb.charAt(N - 1);
        int idx = N - 2;
        while (idx >= 0 && sb.charAt(idx) == last) {
            idx -= 1;
        }
        for (int i = idx; i >= 0; i--) {
            if (sb.charAt(i) == 'R') r += 1;
            else b += 1;
        }
        int temp = Math.min(r, b);

        char first = sb.charAt(0);
        r = 0;
        b = 0;
        idx = 1;
        while (idx < N && sb.charAt(idx) == first) {
            idx += 1;
        }
        for (int i = idx; i < N; i++) {
            if (sb.charAt(i) == 'R') r += 1;
            else b += 1;
        }
        int temp2 = Math.min(r, b);
        result = Math.min(temp, temp2);
        System.out.println(result);
    }
}
