package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 스타트링크_5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] visited = new int[F + 1];
        int[] move = {U, -D};
        int result = bfs(S, G, F, visited, move);
        if (result == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(result);
        }
    }

    public static int bfs(int start, int end, int totalLength, int[] visited, int[] move) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == end) {
                return visited[now] - 1;
            }
            for (int m : move) {
                int next = now + m;
                if (next < 1 || next > totalLength) continue;
                if (visited[next] == 0) {
                    visited[next] = visited[now] + 1;
                    q.add(next);
                }
            }
        }
        return -1;
    }
}
