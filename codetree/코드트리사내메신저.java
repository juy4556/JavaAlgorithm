package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 코드트리사내메신저 {
    static int N;
    static int[] time;
    static int[] visited;
    static List<Integer>[] graph;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        visited = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int boss = Integer.parseInt(st.nextToken());
            graph[boss].add(i + 1);
        }
        dfs(1);

        System.out.println(time[1]);
        System.out.println(Arrays.toString(time));
    }

    private static int dfs(int now) {
        visited[now] = 1;
        if (graph[now].isEmpty()) {
            return 0;
        }
        Queue<Integer> q = new PriorityQueue<>();

        for (int next : graph[now]) {
            if (visited[next] == 1) continue;
            q.add(-dfs(next));
        }
        int order = 1;
        while (!q.isEmpty()) {
            int depthCount = -q.poll();
            time[now] = Math.max(time[now], depthCount + order);
            order++;
        }
        return time[now];
    }
}
