package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 성곽_2234 {
    private static int N, M;
    private static int[][] space;
    private static Map<Integer, Integer> roomWidths;
    private static int roomCount = 0;
    private static int maxRoomWidth = 0;
    private static int maxRoomWidthWithoutAWall = 0;
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[M][N];
        roomWidths = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] visited = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] != 0) {
                    continue;
                }
                roomCount++;
                bfs(i, j, visited);
            }
        }
        findMaxRoomWidthWithOutAWall(visited);

        System.out.println(roomCount);
        System.out.println(maxRoomWidth);
        System.out.println(maxRoomWidthWithoutAWall);
    }

    private static void findMaxRoomWidthWithOutAWall(int[][] visited) {
        boolean[][] visited2 = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited2[i][j]) {
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (notInRange(nx, ny) || visited2[nx][ny]) {
                        continue;
                    }
                    if (visited[i][j] != visited[nx][ny]) {
                        int sum = roomWidths.getOrDefault(visited[i][j], 0) + roomWidths.getOrDefault(visited[nx][ny], 0);
                        maxRoomWidthWithoutAWall = Math.max(maxRoomWidthWithoutAWall, sum);
                    }
                }
                visited2[i][j] = true;
            }
        }
    }

    private static void bfs(int a, int b, int[][] visited) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(a, b));
        int roomWidth = 0;

        while (!q.isEmpty()) {
            Point polled = q.poll();
            int x = polled.x;
            int y = polled.y;

            if (visited[x][y] == -1) {
                continue;
            }
            visited[x][y] = -1;
            roomWidth++;

            for (int i = 0; i < 4; i++) {
                if ((space[x][y] & (1 << i)) > 0) {
                    continue;
                }
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (notInRange(nx, ny) || visited[nx][ny] != 0) {
                    continue;
                }

                q.add(new Point(nx, ny));
            }
        }

        initVisited(visited);
        roomWidths.put(roomCount, roomWidth);
        maxRoomWidth = Math.max(maxRoomWidth, roomWidth);
    }

    private static void initVisited(int[][] visited) {

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == -1) {
                    visited[i][j] = roomCount;
                }
            }
        }
    }

    private static boolean notInRange(int x, int y) {
        return x < 0 || x >= M || y < 0 || y >= N;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
