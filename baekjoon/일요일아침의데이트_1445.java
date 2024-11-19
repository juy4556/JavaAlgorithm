package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 일요일아침의데이트_1445 {
    static int N, M;
    static Character[][] space;
    static int[][] garbages;
    static int[][][] garbageCount;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static class Node implements Comparable<Node> {
        int garbageCount;
        int garbageNextCount;
        int x, y;

        public Node(int garbageCount, int garbageNextCount, int x, int y) {
            this.garbageCount = garbageCount;
            this.garbageNextCount = garbageNextCount;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node node) {
            if (node.garbageCount == this.garbageCount) {
                return this.garbageNextCount - node.garbageNextCount;
            }
            return this.garbageCount - node.garbageCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        garbages = new int[N][M];
        garbageCount = new int[N][M][2];
        space = new Character[N][M];
        int[] startPos = new int[2];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                space[i][j] = input.charAt(j);
                if (space[i][j] == 'S') {
                    startPos[0] = i;
                    startPos[1] = j;
                    space[i][j] = '.';
                }
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (space[i][j] == 'g') {
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (isNotInRange(nx, ny)) {
                            continue;
                        }

                        if (space[nx][ny] == '.') {
                            space[nx][ny] = 'n';
                        }
                    }
                }
            }
        }

        int[] result = bfs(startPos);
        System.out.println(result[0] + " " + result[1]);
    }

    private static boolean isNotInRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    private static int[] bfs(int[] startPos) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, startPos[0], startPos[1]));
        boolean[][] visited = new boolean[N][M];
        visited[startPos[0]][startPos[1]] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int garbageCount = node.garbageCount;
            int garbageNextCount = node.garbageNextCount;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isNotInRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                if (space[nx][ny] == '.') {
                    q.add(new Node(garbageCount, garbageNextCount, nx, ny));
                } else if (space[nx][ny] == 'g') {
                    q.add(new Node(garbageCount + 1, garbageNextCount, nx, ny));
                } else if (space[nx][ny] == 'n') {
                    q.add(new Node(garbageCount, garbageNextCount + 1, nx, ny));
                } else if (space[nx][ny] == 'F') {
                    return new int[]{garbageCount, garbageNextCount};
                }
            }
        }

        return new int[]{0, 0};
    }
}
