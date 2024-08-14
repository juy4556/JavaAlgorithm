package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA {
    static int N, M;
    static StringTokenizer st;
    static List<Integer>[] graph;
    static int[] depth;
    static int[] parent;

    // 트리에서 해당 노드의 depth, 부모 노드 배열 필요
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        parent = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(findLCA(a, b));
        }
    }

    private static void dfs(int now, int p) {
        if (depth[now] > 0) {
            return;
        }
        for (int next : graph[now]) {
            depth[now] = depth[p] + 1;
            parent[now] = p;
            dfs(next, now);
        }
    }

    private static int findLCA(int a, int b) {
        while (depth[a] != depth[b]) {
            if (depth[a] > depth[b]) {
                a = parent[a];
                continue;
            }
            b = parent[b];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
