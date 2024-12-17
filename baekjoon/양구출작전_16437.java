package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 양구출작전_16437 {
    static int N;
    static List<Integer>[] graph;
    static int[] swCount; // sheep & wolf count
    static boolean[] visited;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        swCount = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList();
        }
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            int count = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            if (t.equals("S")) {
                swCount[i] = count;
            } else if (t.equals("W")) {
                swCount[i] = -count;
            }

            graph[parent].add(i);
        }

        System.out.println(dfs(1));
    }

    private static long dfs(int now) {
        long sheepCount = 0;

        for (int next : graph[now]) {
            sheepCount += dfs(next);
        }

        sheepCount += swCount[now];
        if (sheepCount < 0) {
            sheepCount = 0;
        }

        return sheepCount;
    }
}
