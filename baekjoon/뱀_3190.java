package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀_3190 {
    static int N, K, L;
    static int[][] space;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        space = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            space[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        int[] time = new int[L];
        char[] direction = new char[L];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            direction[i] = st.nextToken().charAt(0);
        }

        int x = 0, y = 0, d = 1, t = 0, lIndex = 0;
        Deque<List<Integer>> snake = new ArrayDeque<>();
        snake.add(List.of(0, 0));
        space[x][y] = 2;

        while (true) {
            t++;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!inRange(nx, ny) || space[nx][ny] == 2) {
                break;
            }

            if (space[nx][ny] == 1) {
                space[nx][ny] = 2;
                snake.add(List.of(nx, ny));
            } else if (space[nx][ny] == 0) {
                space[nx][ny] = 2;
                snake.add(List.of(nx, ny));
                List<Integer> tail = snake.poll();
                space[tail.get(0)][tail.get(1)] = 0;
            }

            if (lIndex < L && t == time[lIndex]) {
                if (direction[lIndex] == 'L') {
                    d = (d + 3) % 4;
                } else if (direction[lIndex] == 'D') {
                    d = (d + 1) % 4;
                }

                lIndex++;
            }

            x = nx;
            y = ny;
        }

        System.out.println(t);
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
