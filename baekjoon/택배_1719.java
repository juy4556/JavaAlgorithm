package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 택배_1719 {
    static int n, m;
    static int[][] graph;
    static StringTokenizer st;
    static final int MAX = 1_000_000_000;
    static String[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        result = new String[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = MAX;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], d);
            graph[b][a] = Math.min(graph[b][a], d);
        }

        for (int i = 1; i <= n; i++) {
            int[] dists = new int[n + 1];
            String[] routes = new String[n + 1];

            for (int j = 1; j <= n; j++) {
                dists[j] = MAX;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.add(new int[]{0, i});
            routes[i] = "-";
            dists[i] = 0;

            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                int cost = now[0];
                int node = now[1];

                if (dists[node] < cost) {
                    continue;
                }

                for (int j = 1; j <= n; j++) {
                    if (graph[node][j] == MAX) {
                        continue;
                    }
                    if (dists[j] > cost + graph[node][j]) {
                        dists[j] = cost + graph[node][j];
                        routes[j] = Integer.toString(node);
                        pq.add(new int[]{dists[j], j});
                    }
                }
            }

            for (int j = 1; j <= n; j++) {
                result[j - 1][i - 1] = routes[j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");

            }
            System.out.println();
        }

    }
}
