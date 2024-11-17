package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 거울설치_2151 {
    static int N;
    static Character[][] space;
    static int[] start;
    static int[] end;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static class Node implements Comparable<Node> {
        int x, y, mirrorCount, dir;

        public Node(int x, int y, int mirrorCount, int dir) {
            this.x = x;
            this.y = y;
            this.mirrorCount = mirrorCount;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node node) {
            return this.mirrorCount - node.mirrorCount;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        space = new Character[N][N];
        start = new int[]{-1, -1};
        end = new int[]{-1, -1};
        visited = new boolean[N][N][4];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                space[i][j] = input.charAt(j);
                if (space[i][j] == '#') {
                    if (start[0] == -1 && start[1] == -1) {
                        start[0] = i;
                        start[1] = j;
                    } else {
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }
        }

        int result = bfs();
        System.out.println(result);

    }

    private static int bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        // 시작 위치, 거울 개수, 방향
        for (int i = 0; i < 4; i++) {
            q.add(new Node(start[0], start[1], 0, i));
            visited[start[0]][start[1]][i] = true;
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;
            int mirrorCount = now.mirrorCount;
            int dir = now.dir;

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if (space[nx][ny] == '*' || visited[nx][ny][dir]) {
                continue;
            }

            visited[nx][ny][dir] = true;

            if (space[nx][ny] == '#') {
                if (nx == end[0] && ny == end[1]) {
                    return mirrorCount;
                }
            } else if (space[nx][ny] == '.') {
                q.add(new Node(nx, ny, mirrorCount, dir));
            } else if (space[nx][ny] == '!') {
                q.add(new Node(nx, ny, mirrorCount, dir));
                q.add(new Node(nx, ny, mirrorCount + 1, (dir + 1) % 4));
                q.add(new Node(nx, ny, mirrorCount + 1, (dir + 3) % 4));
            }
        }
        return 0;
    }
}
