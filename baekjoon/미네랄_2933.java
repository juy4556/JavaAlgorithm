package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 미네랄_2933 {
    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int R, C, N;
    static char[][] map;
    static int[][] visited;
    static Set<Pos> cluster = new HashSet<>();
    static int[] throwHeight;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        N = Integer.parseInt(br.readLine());
        throwHeight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            throwHeight[i] = R - Integer.parseInt(st.nextToken());
        }
        throwStick();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void throwStick() {
        for (int i = 0; i < N; i++) {
            int height = throwHeight[i];
            int y = 0;
            cluster.clear();
            if ((i & 1) == 0) {
                for (int j = 0; j < C; j++) {
                    if (map[height][j] == 'x') {
                        map[height][j] = '.';
                        y = j;
                        break;
                    }
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (map[height][j] == 'x') {
                        map[height][j] = '.';
                        y = j;
                        break;
                    }
                }
            }
            if (isConnected(cluster, height, y)) {
                continue;
            }
            dropCluster(cluster);
        }
    }

    public static boolean isConnected(Set<Pos> cluster, int x, int y) {
        visited = new int[R][C];
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] > 0 || map[nx][ny] == '.') {
                continue;
            }
            if (!dfs(nx, ny, visited, cluster)) {
                return false;
            }
            cluster.clear();
        }
        return true;
    }

    public static boolean dfs(int x, int y, int[][] visited, Set<Pos> cluster) {
        visited[x][y] = 1;
        cluster.add(new Pos(x, y));
        if (x == R - 1) {
            visited[x][y] = 2;
            return true;
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] == 1 || map[nx][ny] == '.') {
                continue;
            }
            if (visited[nx][ny] == 2) {
                visited[x][y] = 2;
                return true;
            }
            if (dfs(nx, ny, visited, cluster)) {
                visited[x][y] = 2;
                return true;
            }
        }
        return false;
    }

    public static void dropCluster(Set<Pos> cluster) {
        int dropHeight = R - 1;

        for (Pos c : cluster) {
            int x = c.x;
            int y = c.y;
            dropHeight = Math.min(dropHeight, R - x - 1);
            for (int i = x + 1; i < R; i++) {
                if (cluster.contains(new Pos(i, y))) {
                    break;
                }
                if (map[i][y] == 'x') {
                    dropHeight = Math.min(dropHeight, i - x - 1);
                    break;
                }
            }
        }
        visited = new int[R][C];
        for (Pos c : cluster) {
            int x = c.x;
            int y = c.y;
            if (x + dropHeight >= R) {
                continue;
            }
            map[x + dropHeight][y] = 'x';
            visited[x + dropHeight][y] = 1;
            if (visited[x][y] == 0) {
                map[x][y] = '.';
            }
        }
    }
}
