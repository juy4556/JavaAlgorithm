package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 최소횟수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] dp = new int[100001];

        bfs(dp, a, b);
    }

    private static void bfs(int[] dp, int a, int b) {
        dp[a] = 1;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(a);
        int[] visited = new int[100001];

        while (!dq.isEmpty()) {
            int n = dq.poll();

            if (n == b) {
                System.out.println(visited[n]);
                return;
            }

            if (n * 2 <= 100000 && visited[n * 2] == 0) {
                dq.add(n * 2);
                visited[n * 2] = visited[n];
            }

            if (n + 1 <= 100000 && visited[n + 1] == 0) {
                dq.add(n + 1);
                visited[n + 1] = visited[n] + 1;
            }

            if (n - 1 >= 0 && visited[n - 1] == 0) {
                dq.add(n - 1);
                visited[n - 1] = visited[n] + 1;
            }
        }
    }
}
