package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 배낭에보석담기 {
    static int N, M;
    static StringTokenizer st;
    static double result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(o -> -o.valueRatio));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq.add(new Jewel(w, v));
        }

        while (M > 0 && !pq.isEmpty()) {
            Jewel polled = pq.poll();
            if (polled.w <= M) {
                result += polled.v;
                M -= polled.w;
            } else {
                result += polled.valueRatio * M;
                M = 0;
            }
        }

        System.out.println(String.format("%.3f", result));
    }

    static class Jewel {
        int w;
        int v;
        double valueRatio;

        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
            this.valueRatio = (double) v / w;
        }
    }
}
