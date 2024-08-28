package baekjoon;

import java.io.*;
import java.util.*;

public class 최소스패닝트리 {
    static int V, E;
    static int[] parent;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.c));
        parent = new int[V + 1];
        long result = 0;

        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(a, b, c);
            pq.add(edge);
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (findParent(edge.a) == findParent(edge.b)) {
                continue;
            }
            unionParent(edge.a, edge.b);
            result += edge.c;
        }

        System.out.println(result);
    }

    private static class Edge {
        int a, b, c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private static int findParent(int x) {
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
