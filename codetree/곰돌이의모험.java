package codetree;

import java.util.*;
import java.io.*;

public class 곰돌이의모험 {
    static int N, M;
    static StringTokenizer st;
    static int[][] space;
    static int[][] visited;
    static int[][] pos;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][N];
        visited = new int[N][N];
        pos = new int[M + 1][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken()) - 1;
            pos[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        dfs(0, new ArrayList<>());
        System.out.println(result);
    }

    private static void dfs(int depth, List<Integer> dirs) {
        if (depth == 3 * (M + 1)) {
            int temp = 0;

            for (int i = 0; i <= M; i++) {
                temp += space[pos[i][0]][pos[i][1]];
                visited[pos[i][0]][pos[i][1]] = 1;
            }

            for (int k = 0; k <= M; k++) {
                int x = pos[k][0];
                int y = pos[k][1];
                for (int i = 3 * k; i < 3 * k + 3; i++) {
                    x += dx[dirs.get(i)];
                    y += dy[dirs.get(i)];
                    if (!inRange(x, y) || space[x][y] == -1) {
                        break;
                    }
                    if (visited[x][y] == 0) {
                        visited[x][y] = 1;
                        temp += space[x][y];
                    }
                }
            }
            result = Math.max(result, temp);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited[i][j] = 0;
                }
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            dirs.add(d);
            dfs(depth + 1, dirs);
            dirs.remove(dirs.size() - 1);
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
