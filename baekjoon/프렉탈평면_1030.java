package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 프렉탈평면_1030 {
    static int time, n, k, r1, r2, c1, c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        time = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        int fullSize = (int) Math.pow(n, time);

        for (int x = r1; x <= r2; x++) {
            for (int y = c1; y <= c2; y++) {
                System.out.print(divideAndConquer(fullSize, x, y));
            }
            System.out.println();
        }
    }

    private static int divideAndConquer(int size, int x, int y) {
        if (size == 1) {
            return 0;
        }
        size /= n;
        int blackStart = size * (n - k) / 2;
        int blackEnd = size * (n + k) / 2;
        if (x >= blackStart && x < blackEnd && y >= blackStart && y < blackEnd) {
            return 1;
        }
        return divideAndConquer(size, x % size, y % size);
    }
}
