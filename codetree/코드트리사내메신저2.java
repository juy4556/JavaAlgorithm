package codetree;

import java.io.*;
import java.util.*;

public class 코드트리사내메신저2 {
    static int N;
    static int[] time;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(input[i]);
            graph[parent].add(i + 1);
        }

        dfs(1);
        System.out.println(time[1]);
    }

    private static void dfs(int now) {
        if (graph[now].isEmpty()) {
            time[now] = 0;
            return;
        }

        int t = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int next : graph[now]) {
            dfs(next);
            pq.add(-time[next]);
        }

        int order = 1;
        // 오래 걸리는 순서대로 처리
        while (!pq.isEmpty()) {
            Integer takeTime = -pq.poll();
            t = Math.max(t, takeTime + order);
            order++;
        }
        time[now] = t;
    }
}
