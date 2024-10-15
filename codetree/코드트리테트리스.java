package codetree;

import java.io.*;
import java.util.*;

public class 코드트리테트리스 {
    static int n, m;
    static int[][] space;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringTokenizer st;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        space = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = 1;
                Point p = new Point(i, j);
                points.add(p);
                findMaxSum(points);
                points.remove(p);
                visited[i][j] = 0;
            }
        }

        System.out.println(result);
    }

    private static void findMaxSum(List<Point> points) {
        if (points.size() == 5) {
            Map<Integer, Integer> xCount = new HashMap<>();
            Map<Integer, Integer> yCount = new HashMap<>();
            int sum = 0;

            for (Point p : points) {
                xCount.put(p.x, xCount.getOrDefault(p.x, 0) + 1);
                yCount.put(p.y, yCount.getOrDefault(p.y, 0) + 1);
                if (xCount.getOrDefault(p.x, 0) >= 4 || yCount.getOrDefault(p.y, 0) >= 4) {
                    return;
                }
                sum += space[p.x][p.y];
            }

            result = Math.max(result, sum);
            return;
        }

        int pointSize = points.size();

        for (int i = 0; i < pointSize; i++) {
            int x = points.get(i).x;
            int y = points.get(i).y;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!inRange(nx, ny) || visited[nx][ny] == 1) {
                    continue;
                }
                visited[nx][ny] = 1;
                points.add(new Point(nx, ny));
                findMaxSum(points);
                points.remove(points.size() - 1);
                visited[nx][ny] = 0;
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
