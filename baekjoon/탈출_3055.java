package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class 탈출_3055 {
    static private class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C;
    static char[][] space;
    static Queue<Pos> waterQ;
    static Queue<Pos> hedgehogQ;
    static int[] sPos = {0, 0};
    static int[] dPos = {0, 0};
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        space = new char[R][C];
        waterQ = new ArrayDeque<>();
        hedgehogQ = new ArrayDeque<>();
        int[][] hedgehogVisited = new int[R][C];

        for (int i = 0; i < R; i++) {
            space[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (space[i][j] == 'S') {
                    hedgehogQ.add(new Pos(i, j));
                    hedgehogVisited[i][j] = 1;
                } else if (space[i][j] == 'D') {
                    dPos[0] = i;
                    dPos[1] = j;
                } else if (space[i][j] == '*') {
                    waterQ.add(new Pos(i, j));
                }
            }
        }

        while (!hedgehogQ.isEmpty()) {
            int waterLen = waterQ.size();
            for (int i = 0; i < waterLen; i++) {
                Pos p = waterQ.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || space[nx][ny] == '*' || space[nx][ny] == 'X' || space[nx][ny] == 'D') {
                        continue;
                    }
                    space[nx][ny] = '*';
                    waterQ.add(new Pos(nx, ny));
                }
            }

            int hedgehogLen = hedgehogQ.size();
            for (int i = 0; i < hedgehogLen; i++) {
                Pos p = hedgehogQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || hedgehogVisited[nx][ny] > 0 || space[nx][ny] == '*' || space[nx][ny] == 'X') {
                        continue;
                    }
                    hedgehogVisited[nx][ny] = hedgehogVisited[p.x][p.y] + 1;
                    if (nx == dPos[0] && ny == dPos[1]) {
                        System.out.println(hedgehogVisited[nx][ny] - 1);
                        return;
                    }

                    hedgehogQ.add(new Pos(nx, ny));
                }
            }

        }

        System.out.println("KAKTUS");
    }
}
