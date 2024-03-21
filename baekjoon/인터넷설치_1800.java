package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인터넷설치_1800 {
    static int N, P, K;
    static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        int result = binarySearch(0, 1_000_000);
        if (result <= 1_000_000) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    private static int binarySearch(int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (dijkstra(mid) <= K) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static int dijkstra(int standard) {
        int[] chanceCount = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            chanceCount[i] = Integer.MAX_VALUE;
        }
        chanceCount[1] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(P, Comparator.comparingInt(a -> a[0]));
        q.add(new int[]{0, 1});
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int cost = polled[0];
            int now = polled[1];
            if (chanceCount[now] < cost) {
                continue;
            }
            for (int[] nodes : graph.get(now)) {
                int n = nodes[0];
                int c = nodes[1];
                if (c > standard) {
                    if (chanceCount[n] > cost + 1) {
                        chanceCount[n] = cost + 1;
                        q.add(new int[]{cost + 1, n});
                    }
                } else {
                    if (chanceCount[n] > cost) {
                        chanceCount[n] = cost;
                        q.add(new int[]{cost, n});
                    }
                }
            }
        }
        return chanceCount[N];
    }
}
