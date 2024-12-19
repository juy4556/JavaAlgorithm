package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 내리막길_1520 {
    static int N, M;
    static int[][] space;
    static int[][] dp;
    static StringTokenizer st;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Node implements Comparable<Node> {
        int num, x, y;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node o) {
            return o.num - this.num;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        space = new int[M][N];
        dp = new int[M][N];
        dp[M - 1][N - 1] = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        System.out.println(dfs(0, 0));
        System.out.println(bfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] > 0) {
            return dp[x][y];
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= M || ny < 0 || ny >= N || dp[nx][ny] == -1) {
                continue;
            }
            if (space[nx][ny] < space[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        if (dp[x][y] == 0) {
            dp[x][y] = -1;
            return 0;
        }

        return dp[x][y];
    }

    private static int bfs(int x, int y) {
        int[][] visited = new int[M][N];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(space[x][y], x, y));
        visited[x][y] = 1;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int num = now.num;
            x = now.x;
            y = now.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                    continue;
                }
                if (space[nx][ny] < space[x][y]) {
                    if (visited[nx][ny] == 0) {
                        visited[nx][ny] += visited[x][y];
                        pq.add(new Node(space[nx][ny], nx, ny));
                    } else {
                        visited[nx][ny] += visited[x][y];
                    }
                }
            }
        }

        return visited[M - 1][N - 1];
    }
}
