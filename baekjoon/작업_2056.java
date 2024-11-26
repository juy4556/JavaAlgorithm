package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 작업_2056 {
    static int N;
    static StringTokenizer st;
    static int[] times;
    static int[] indegree;
    static ArrayList[] graph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = new int[N + 1];
        indegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int pre = Integer.parseInt(st.nextToken());
                graph[pre].add(i + 1);
                indegree[i + 1]++;
            }

            times[i + 1] = time;
        }

        System.out.println(topologicalSort());
    }

    private static int topologicalSort() {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                dp[i] = times[i];
            }
        }

        while (!q.isEmpty()) {
            Integer num = q.poll();

            for (int i = 0; i < graph[num].size(); i++) {
                int next = (int) graph[num].get(i);
                indegree[next]--;
                dp[next] = Math.max(dp[next], dp[num] + times[next]);
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
