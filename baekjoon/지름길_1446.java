package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 지름길_1446 {
    public static class ShortCut {
        private int start;
        private int end;
        private int length;

        public ShortCut(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getLength() {
            return length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        ShortCut[] shortCuts = new ShortCut[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            shortCuts[i] = new ShortCut(start, end, length);
        }
        Arrays.sort(shortCuts, Comparator.comparingInt(ShortCut::getEnd));
        int[] dp = new int[10001];
        for (int i = 1; i <= D; i++) {
            dp[i] = dp[i - 1] + 1;
            for (ShortCut shortCut : shortCuts) {
                if (shortCut.getEnd() > i) {
                    break;
                }
                if (shortCut.getEnd() == i) {
                    dp[i] = Math.min(dp[i], dp[shortCut.getStart()] + shortCut.getLength());
                }
            }
        }
        System.out.print(dp[D]);
    }
}
