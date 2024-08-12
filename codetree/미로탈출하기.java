package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 미로탈출하기 {
    static int N;
    static int x, y, dir;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] pos = br.readLine().split(" ");
        x = Integer.parseInt(pos[0]) - 1;
        y = Integer.parseInt(pos[1]) - 1;
        dir = 1;
        char[][] space = new char[N][N];
        int[][][] visited = new int[N][N][4];
        visited[x][y][1] = 1;

        for (int i = 0; i < N; i++) {
            space[i] = br.readLine().toCharArray();
        }

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            int rightX = nx + dx[(dir + 1) % 4];
            int rightY = ny + dy[(dir + 1) % 4];

            if (!inRange(nx, ny)) {
                time++;
                break;
            }

            if (visited[nx][ny][dir] > 0 || visited[x][y][dir] > 1) {
                time = -1;
                break;
            }

            if (space[nx][ny] == '#') {
                dir = (dir + 3) % 4;
                visited[x][y][dir] += 1;
                continue;
            }

            if (space[rightX][rightY] == '#') {
                x = nx;
                y = ny;
                time++;
                visited[nx][ny][dir] += 1;
            } else {
                x = rightX;
                y = rightY;
                time += 2;
                visited[nx][ny][dir] += 1;
                dir = (dir + 1) % 4;
                visited[rightX][rightY][dir] += 1;
            }

        }
        System.out.println(time);
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
