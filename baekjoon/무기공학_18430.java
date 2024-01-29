package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 무기공학_18430 {
    static int N;
    static int M;
    static int[][] space;
    static int[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int result = 0;

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
            }
        }
        dfs(0, 0, 0);
        System.out.println(result);
    }

    public static void dfs(int x, int y, int strength) {
        if (y == M) {
            x++;
            y = 0;
        }
        if (x == N) {
            result = Math.max(result, strength);
            return;
        }
        if (visited[x][y] == 0) {
            for (int d = 0; d < 4; d++) {
                int nx1 = x + dx[d];
                int ny1 = y + dy[d];
                int nx2 = x + dx[(d + 1) % 4];
                int ny2 = y + dy[(d + 1) % 4];
                if (nx1 < 0 || ny1 < 0 || nx1 > N - 1 || ny1 > M - 1 || visited[nx1][ny1] == 1) continue;
                if (nx2 < 0 || ny2 < 0 || nx2 > N - 1 || ny2 > M - 1 || visited[nx2][ny2] == 1) continue;
                visited[nx1][ny1] = 1;
                visited[nx2][ny2] = 1;
                visited[x][y] = 1;
                int sum = space[x][y] * 2 + space[nx1][ny1] + space[nx2][ny2];
                dfs(x, y + 1, strength + sum);
                visited[x][y] = 0;
                visited[nx1][ny1] = 0;
                visited[nx2][ny2] = 0;
            }
        }
        dfs(x, y + 1, strength);
    }
}
