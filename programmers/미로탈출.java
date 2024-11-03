package programmers;

import java.util.ArrayList;
import java.util.List;

public class 미로탈출 {

    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int[][] space;
    static String answer = "impossible";

    public static void main(String[] args) {
//        System.out.println(solution(3, 4, 2, 3, 3, 1, 5)); // "dllrl"
//        System.out.println(solution(2, 2, 1, 1, 2, 2, 2)); // "dr"
        System.out.println(solution(3, 3, 1, 2, 3, 3, 4)); // "impossible"
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        space = new int[n][m];
        int sx = x - 1;
        int sy = y - 1;
        int ex = r - 1;
        int ey = c - 1;

        dfs(sx, sy, ex, ey, k, new StringBuilder());

        return answer;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < space.length && y >= 0 && y < space[0].length;
    }

    private static boolean dfs(int x, int y, int ex, int ey, int k, StringBuilder sb) {
        if (sb.length() == k) {
            if (x == ex && y == ey) {
                answer = sb.toString();
                return true;  // done
            }
            return false;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            int leftDist = Math.abs(ex - nx) + Math.abs(ey - ny);

            if (!inRange(nx, ny)) {
                continue;
            }

            if (leftDist % 2 != (k - sb.length() - 1) % 2 || leftDist > k - sb.length() - 1) {
                continue;
            }

            sb.append(dir[d]);
            if (dfs(nx, ny, ex, ey, k, sb)) {
                return true;
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return false;
    }
}
