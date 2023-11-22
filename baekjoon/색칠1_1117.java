package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색칠1_1117 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        long result = (long) W * H;
        int y = W - f;
        int x = H / (c + 1);
        int xMul = H / x;
        int[] yFoldedSection = new int[2];
        if (f == W || f == 0) {
            y = W;
            yFoldedSection[0] = (int) Math.pow(10, 9) + 1;
            yFoldedSection[1] = -1;
        } else {
            if (W - f >= f) {
                y = W - f;
                yFoldedSection[1] = f;
            } else {
                y = f;
                yFoldedSection[1] = W - f;
            }
        }

        if (yFoldedSection[1] < y1) {
            result -= (long) (x2 - x1) * (y2 - y1) * xMul;
        } else if (yFoldedSection[1] > y2) {
            result -= (long) (x2 - x1) * (y2 - y1) * 2 * xMul;
        } else {
            result -= (long) (x2 - x1) * (yFoldedSection[1] - y1) * 2 * xMul;
            result -= (long) (x2 - x1) * (y2 - yFoldedSection[1]) * xMul;
        }
        System.out.println(result);
    }
}
