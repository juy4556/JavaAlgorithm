package codetree;

import java.io.*;
import java.util.*;

public class nxm표이동9 {
    static int n, m;
    static int[][] space;
    static int[][][] visited;
    static StringTokenizer st;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int MAX = 1_000_000_000;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        space = new int[n][m];
        visited = new int[n][m][2];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j][0] = MAX;
                visited[i][j][1] = MAX;
            }
        }

        if (space[0][0] == 1 && space[n - 1][m - 1] == 1) {
            System.out.println(-1);
            return;
        }

        Queue<Pos> q = new ArrayDeque();
        if (space[0][0] == 1) {
            q.add(new Pos(0, 0, 1));
            visited[0][0][1] = 1;
        } else {
            q.add(new Pos(0, 0, 0));
            visited[0][0][0] = 1;
        }

        while (!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int hasOne = p.hasOne;

            if (x == n - 1 && y == m - 1) {
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!inRange(nx, ny) || visited[nx][ny][hasOne] <= visited[x][y][hasOne] + 1) {
                    continue;
                }

                if (hasOne == 1 && space[nx][ny] == 1) {
                    continue;
                }

                visited[nx][ny][hasOne | space[nx][ny]] = visited[x][y][hasOne] + 1;
                q.add(new Pos(nx, ny, hasOne | space[nx][ny]));
            }
        }

        result = Math.min(visited[n - 1][m - 1][0], visited[n - 1][m - 1][1]);
        if (result < MAX) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static class Pos {
        int x, y;
        int hasOne;

        public Pos(int x, int y, int hasOne) {
            this.x = x;
            this.y = y;
            this.hasOne = hasOne;
        }
    }
}
