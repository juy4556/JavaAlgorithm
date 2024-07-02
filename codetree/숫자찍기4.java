package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 숫자찍기4 {
    static int n;
    static int[][] space;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        space = new int[n][n];
        solve();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(space[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solve() {
        int num = 1;
        int x = 0;
        int y = 0;
        int d = 0;
        space[x][y] = num++;

        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny) || space[nx][ny] != 0) {
                d = (d + 1) % 4;
                nx = x + dx[d];
                ny = y + dy[d];
                if (!inRange(nx, ny) || space[nx][ny] != 0) {
                    break;
                }
            }
            space[nx][ny] = num++;
            x = nx;
            y = ny;
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
