package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다_1937 {
    static int n;
    static int[][] space;
    static int[][] dp;
    static StringTokenizer st;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        space = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j] + " ");
                result = Math.max(result, dp[i][j]);
            }
//            System.out.println();
        }

        System.out.println(result);
    }

    private static void dfs(int x, int y) {
//        System.out.println("x: " + x + ", y: " + y);
        if (dp[x][y] > 0) {
            return;
        }
        dp[x][y] = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny)) {
                continue;
            }
            if (space[nx][ny] > space[x][y]) {
                dfs(nx, ny);
                dp[x][y] = Math.max(dp[x][y], dp[nx][ny] + 1);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
