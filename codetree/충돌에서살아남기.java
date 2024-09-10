package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 충돌에서살아남기 {
    static int T, N, M;
    static int[][] space;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Integer, Integer> bombDir;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            space = new int[N][N];
            bombDir = new HashMap<>();
            int bombNum = 1;
            int bombCount = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                String dir = st.nextToken();
                if (dir.equals("U")) {
                    bombDir.put(bombNum, 0);
                } else if (dir.equals("D")) {
                    bombDir.put(bombNum, 2);
                } else if (dir.equals("R")) {
                    bombDir.put(bombNum, 1);
                } else {
                    bombDir.put(bombNum, 3);
                }
                space[x - 1][y - 1] = bombNum;
                bombNum++;
            }

            moveBombs();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (space[i][j] > 0) {
                        bombCount++;
                    }
                }
            }
            System.out.println(bombCount);

        }


    }

    private static void moveBombs() {

        for (int k = 0; k < 2 * N; k++) {
            int[][] newSpace = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (space[i][j] == 0) {
                        continue;
                    }
                    int dir = bombDir.get(space[i][j]);
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if (!inRange(nx, ny)) {
                        bombDir.put(space[i][j], (dir + 2) % 4);
                        if (newSpace[i][j] == 0) {
                            newSpace[i][j] = space[i][j];
                        } else {
                            newSpace[i][j] = -1; // 폭발
                        }
                        continue;
                    }
                    if (newSpace[nx][ny] == 0) {
                        newSpace[nx][ny] = space[i][j];
                        continue;
                    }
                    newSpace[nx][ny] = -1; // 폭발
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newSpace[i][j] == -1) {
                        newSpace[i][j] = 0;
                    }
                    space[i][j] = newSpace[i][j];
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
