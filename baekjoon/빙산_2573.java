package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 빙산_2573 {
    static int N, M;
    static int[][] space;
    static int[][] visited;
    static int[][] melting;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int year = 0;
    static List<int[]> icebergs = new ArrayList<>(10000);
    static int connectCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] != 0) {
                    icebergs.add(new int[]{i, j});
                }
            }
        }
        while (true) {
            melting = new int[N][M];
            int icebergSize = icebergs.size();
            for (int i = 0; i < icebergSize; i++) {
                int[] iceberg = icebergs.get(i);
                int x = iceberg[0];
                int y = iceberg[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dir[d][0];
                    int ny = y + dir[d][1];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (space[nx][ny] == 0) {
                        melting[x][y]++;
                    }
                }
            }
            for (int i = 0; i < icebergSize; i++) {
                int[] iceberg = icebergs.get(i);
                int x = iceberg[0];
                int y = iceberg[1];
                space[x][y] -= melting[x][y];
                if (space[x][y] <= 0) {
                    space[x][y] = 0;
                    icebergs.remove(i);
                    i--;
                    icebergSize--;
                }
            }
            if (icebergSize == 0) {
                break;
            }
            year++;
            visited = new int[N][M];
            int[] iceberg = icebergs.get(0);
            visited[iceberg[0]][iceberg[1]] = 1;
            connectCount = 1;
            dfs(iceberg, visited, icebergSize);
            if (connectCount != icebergSize) {
                break;
            }
        }
        if (icebergs.size() == 0) {
            year = 0;
        }
        System.out.println(year);
    }

    private static boolean dfs(int[] iceberg, int[][] visited, int goal) {
        if (connectCount == goal) {
            return true;
        }
        int x = iceberg[0];
        int y = iceberg[1];
        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (space[nx][ny] > 0 && visited[nx][ny] == 0) {
                connectCount++;
                visited[nx][ny] = 1;
                dfs(new int[]{nx, ny}, visited, goal);
            }
        }
        return false;
    }
}
