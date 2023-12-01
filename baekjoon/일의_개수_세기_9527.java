package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

public class 일의_개수_세기_9527 {
    static long[] oneCount = new long[55];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        for (int i = 1; i < 55; i++) {
            oneCount[i] = (long) (i * pow(2, i - 1));
        }

        System.out.println(countOne(B) - countOne(A - 1));
    }

    private static long countOne(long num) {
        long count = num & 1;
        long p = 1L << 56;
        for (int i = 55; i > 0; i--) {
            p >>= 1;
            if ((num & p) > 0) {
                count += oneCount[i];
                count += num - (num & p) + 1;
                num -= p;
            }
        }
        return count;
    }
}
