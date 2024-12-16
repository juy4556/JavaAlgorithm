package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ㄷㄷㄷㅈ_19535 {
    static int N;
    static List<Integer>[] graph;
    static List<int[]> edges;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        edges = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        long dCount = 0;
        long gCount = 0;

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
            edges.add(new int[]{u, v});
        }

        for (int i = 1; i <= N; i++) {
            long adjacentNodesCount = graph[i].size();
            gCount += adjacentNodesCount * (adjacentNodesCount - 1) * (adjacentNodesCount - 2) / 6;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            dCount += (graph[u].size() - 1) * (graph[v].size() - 1);

        }

        if (dCount > 3 * gCount) {
            System.out.println("D");
            return;
        }

        if (dCount < 3 * gCount) {
            System.out.println("G");
            return;
        }

        System.out.println("DUDUDUNGA");
    }
}
