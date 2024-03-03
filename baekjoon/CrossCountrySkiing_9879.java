package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CrossCountrySkiing_9879 {
    static int M, N;
    static int[][] space;
    static int[][] visited;
    static List<int[]> wayPoints = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        space = new int[M][N];
        visited = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    wayPoints.add(new int[]{i, j});
                }
            }
        }
        System.out.println(binarySearch());
    }

    public static int binarySearch() {
        int start = 0;
        int end = 1_000_000_000;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (bfs(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static boolean bfs(int num) {
        visited = new int[M][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(wayPoints.get(0));
        visited[wayPoints.get(0)[0]][wayPoints.get(0)[1]] = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny] == 1) {
                    continue;
                }
                if (Math.abs(space[nx][ny] - space[x][y]) <= num) {
                    visited[nx][ny] = 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        for (int[] wayPoint : wayPoints) {
            if (visited[wayPoint[0]][wayPoint[1]] == 0) {
                return false;
            }
        }
        return true;
    }
}
