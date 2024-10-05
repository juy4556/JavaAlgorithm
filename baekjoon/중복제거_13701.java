package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 중복제거_13701 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[(1 << (25 - 5))];
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            int a = n / 32;
            int b = n % 32;
            if ((A[a] & (1 << b)) == 0) {
                A[a] += (1 << b);
                System.out.print(n + " ");
            }
        }
    }
}
