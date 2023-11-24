package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 쉬운_최단거리_14940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int[][] space = new int[n][m];
        int[][] visited = new int[n][m];
        int[] start = new int[2];
        Queue<List<Integer>> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 2) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        bfs(space, visited, start, q, dx, dy);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (space[i][j] == 1 && visited[i][j] == 0) {
                    sb.append(-1).append(' ');
                    continue;
                }
                sb.append(visited[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void bfs(int[][] space, int[][] visited, int[] start, Queue<List<Integer>> q, int[] dx, int[] dy) {
        q.add(Arrays.asList(start[0], start[1]));
        visited[start[0]][start[1]] = 0;
        while (!q.isEmpty()) {
            List<Integer> polled = q.poll();
            int x = polled.get(0);
            int y = polled.get(1);
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx > space.length - 1 || ny < 0 || ny > space[0].length - 1) {
                    continue;
                }
                if (visited[nx][ny] == 0 && space[nx][ny] == 1) {
                    visited[nx][ny] = visited[x][y] + 1;
                    q.add(Arrays.asList(nx, ny));
                }
            }
        }
    }
}
