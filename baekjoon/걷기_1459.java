package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 걷기_1459 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        long result = 0;
        if (2 * W < S) {
            result = (long) (X + Y) * W;
        } else {
            int min = Math.min(X, Y);
            int max = Math.max(X, Y);
            result = (long) min * S;
            if (W < S) {
                result += (long) (max - min) * W;
            } else {
                if ((max - min) % 2 == 1) {
                    result += (long) (max - min - 1) * S;
                    result += W;
                } else {
                    result += (long) (max - min) * S;
                }
            }
        }
        System.out.println(result);
    }
}
