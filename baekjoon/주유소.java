package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 주유소 {
    static int N;
    static int[] dist;
    static int[] price;
    static long result = 0L;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N - 1];
        price = new int[N];
        Queue<int[]> mostCheap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            mostCheap.add(new int[]{i, price[i]});
        }
        price[N - 1] = Integer.parseInt(st.nextToken());

        int end = N - 1;
        while (!mostCheap.isEmpty() && end > 0) {
            int[] poll = mostCheap.poll();
            int start = poll[0];
            int minPrice = poll[1];
            if (end <= start) {
                continue;
            }

            result += (long) minPrice * sumDist(start, end);
            end = start;
        }

        System.out.println(result);
    }

    private static long sumDist(int start, int end) {
        long sum = 0L;
        for (int i = start; i < end; i++) {
            sum += dist[i];
        }
        return sum;
    }
}
