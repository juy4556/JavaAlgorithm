package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법의숲탐색 {
    static int R, C, K;
    static int[][] space;
    static StringTokenizer st;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] points;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        space = new int[R + 3][C];
        points = new int[K + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            move(c - 1, d, i + 1);
//            for (int j = 0; j < R + 3; j++) {
//                for (int k = 0; k < C; k++) {
//                    System.out.print(space[j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("result: " + result);
//            System.out.println("====================================");
        }
//        System.out.println("points: " + Arrays.toString(points));
        System.out.println(result);
    }

    private static void move(int c, int d, int num) {
        int r = 1;

        while (true) {
            if (checkDown(r, c)) {
                r += 1;
                moveDown(r, c, d, num);
            } else if (checkLeft(r, c)) {
                int[] rcd = rotate270(r, c, d, num);
                r = rcd[0];
                c = rcd[1];
                d = rcd[2];
            } else if (checkRight(r, c)) {
                int[] rcd = rotate90(r, c, d, num);
                r = rcd[0];
                c = rcd[1];
                d = rcd[2];
            } else {
                break;
            }
        }

        if (r - 1 < 3) {
            for (int i = 0; i < R + 3; i++) {
                for (int j = 0; j < C; j++) {
                    space[i][j] = 0;
                }
            }
            return;
        }


        result += getPoint(r, c, d);
    }

    private static int getPoint(int r, int c, int d) {
        points[space[r][c]] = r - 1;
        int[][] visited = new int[R + 3][C];
        visited[r][c] = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            points[space[r][c]] = Math.max(points[space[r][c]], now[0] - 2);
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (!inRange(nx, ny) || visited[nx][ny] == 1 || space[nx][ny] == 0) {
                    continue;
                }
                if (space[now[0]][now[1]] == space[nx][ny] || space[now[0]][now[1]] == -space[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = 1;
                } else if (space[now[0]][now[1]] != space[nx][ny] && space[now[0]][now[1]] < 0) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = 1;
                }
            }
        }

        return points[space[r][c]];
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R + 3 && 0 <= c && c < C;
    }

    private static boolean checkDown(int r, int c) {
        return inRange(r + 2, c) && space[r + 2][c] == 0 && inRange(r + 1, c - 1) && space[r + 1][c - 1] == 0
                && inRange(r + 1, c + 1) && space[r + 1][c + 1] == 0;
    }

    private static boolean checkLeft(int r, int c) {
        if (!inRange(r - 1, c - 1) || space[r - 1][c - 1] != 0) {
            return false;
        }
        if (!inRange(r, c - 2) || space[r][c - 2] != 0) {
            return false;
        }
        if (!inRange(r + 1, c - 1) || space[r + 1][c - 1] != 0) {
            return false;
        }
        if (!inRange(r + 1, c - 2) || space[r + 1][c - 2] != 0) {
            return false;
        }
        if (!inRange(r + 2, c - 1) || space[r + 2][c - 1] != 0) {
            return false;
        }
        return true;
    }

    private static boolean checkRight(int r, int c) {
        if (!inRange(r - 1, c + 1) || space[r - 1][c + 1] != 0) {
            return false;
        }
        if (!inRange(r, c + 2) || space[r][c + 2] != 0) {
            return false;
        }
        if (!inRange(r + 1, c + 1) || space[r + 1][c + 1] != 0) {
            return false;
        }
        if (!inRange(r + 1, c + 2) || space[r + 1][c + 2] != 0) {
            return false;
        }
        if (!inRange(r + 2, c + 1) || space[r + 2][c + 1] != 0) {
            return false;
        }
        return true;
    }

    private static void moveDown(int r, int c, int d, int n) {
        space[r - 2][c] = 0;
        space[r - 1][c - 1] = 0;
        space[r - 1][c + 1] = 0;
        space[r + 1][c] = n;
        space[r][c - 1] = n;
        space[r][c + 1] = n;
        space[r - 1][c] = n;
        space[r][c] = n;
        space[r + dx[d]][c + dy[d]] = -n;
    }

    private static int[] rotate270(int r, int c, int d, int n) {
        space[r - 1][c] = 0;
        space[r][c] = 0;
        space[r][c + 1] = 0;

        r += 1;
        c -= 1;
        space[r - 1][c] = n;
        space[r][c] = n;
        space[r + 1][c] = n;
        space[r][c - 1] = n;
        space[r][c + 1] = n;
        d = (d - 1) % 4;
        if (d < 0) {
            d += 4;
        }
        space[r + dx[d]][c + dy[d]] = -n;
        return new int[]{r, c, d};
    }

    private static int[] rotate90(int r, int c, int d, int n) {
        space[r - 1][c] = 0;
        space[r][c] = 0;
        space[r][c - 1] = 0;

        r += 1;
        c += 1;
        space[r - 1][c] = n;
        space[r][c] = n;
        space[r + 1][c] = n;
        space[r][c - 1] = n;
        space[r][c + 1] = n;
        d = (d + 1) % 4;
        space[r + dx[d]][c + dy[d]] = -n;
        return new int[]{r, c, d};
    }

}
