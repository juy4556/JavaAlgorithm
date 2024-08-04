package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {
    static int N, M;
    static int r, c, d;
    static int[][] space;
    static StringTokenizer st;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(clean());
    }

    private static int clean() {
        int cleanCount = 0;
        while (true) {
            if (space[r][c] == 0) {
                space[r][c] = 2;
                cleanCount++;
            }
            boolean isClean = true;
            int tempD = d;
            for (int i = 0; i < 4; i++) {
                tempD = (tempD + 1) % 4;
                int nr = r + dx[tempD];
                int nc = c + dy[tempD];
                if (space[nr][nc] == 0) {
                    isClean = false;
                    break;
                }
            }
            if (!isClean) {
                d = (d + 3) % 4;
                int nr = r + dx[d];
                int nc = c + dy[d];
                if (space[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                }
                continue;
            }


            int nr = r + dx[(d + 2) % 4];
            int nc = c + dy[(d + 2) % 4];
            if (space[nr][nc] == 2) {
                r = nr;
                c = nc;
            } else if (space[nr][nc] == 1) {
                break;
            }
        }
        return cleanCount;
    }

}
