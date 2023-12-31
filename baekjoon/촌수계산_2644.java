package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 촌수계산_2644 {
    static ArrayList<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        int result = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt((br.readLine()));  // 전체 사람의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());  // 사람 1
        int b = Integer.parseInt(st.nextToken());  // 사람 2
        int m = Integer.parseInt(br.readLine());  // 부모 자식들 간의 관계의 개수
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        visited = new int[n + 1];
        result = bfs(a, b);
        System.out.println(result);
    }

    public static int bfs(int start, int end) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == end) {
                return visited[now] - 1;
            }
            for (int i = 0; i < graph[now].size(); i++) {
                int node = graph[now].get(i);
                if (visited[node] == 0) {
                    visited[node] = visited[now] + 1;
                    q.add(node);
                }
            }
        }
        return -1;
    }
}
