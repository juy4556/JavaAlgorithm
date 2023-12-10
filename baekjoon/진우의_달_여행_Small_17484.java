package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 진우의_달_여행_Small_17484 {
    static int result = 1201;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] space = new int[N][M];
        int[] dy = {-1, 0, 1};
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            dfs(0, i, -1, 0, N, M, space, dy);
        }
        System.out.println(result);
    }

    private static void dfs(int x, int y, int dir, int total, int limit, int m, int[][] space, int[] dy) {
        total += space[x][y];
        if (x == limit - 1) {
            result = Math.min(result, total);
            return;
        }
        for (int d = 0; d < 3; d++) {
            if (d == dir) continue;
            int ny = y + dy[d];
            if (ny < 0 || ny > m - 1) continue;
            dfs(x + 1, y + dy[d], d, total, limit, m, space, dy);
        }
    }
}
