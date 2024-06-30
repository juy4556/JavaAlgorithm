package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 목표지점 {
    static int n, m;
    static int[][] space;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        space = new int[n][m];
        visited = new int[n][m];
        int[] start = new int[2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
                if (space[i][j] == 2) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        bfs(start);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (space[i][j] == 0) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(visited[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int[] start) {
        visited[start[0]][start[1]] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            int[] pts = q.pollFirst();
            int x = pts[0];
            int y = pts[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!inRange(nx, ny) || visited[nx][ny] >= 0 || space[nx][ny] == 0) continue;
                if (space[nx][ny] == 1) {
                    visited[nx][ny] = visited[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
