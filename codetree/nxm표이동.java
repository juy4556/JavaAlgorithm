package codetree;
import java.util.*;
import java.io.*;

public class nxm표이동 {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] space;
    static int[][] dp;
    static int h, w;
    static int result = 0;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        space = new int[h][w];
        dp = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < w; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = dfs(0,0);
        System.out.println(result);
    }

    public static boolean inRange(int x, int y) {
        if (x < 0 || x >= h || y < 0 || y >= w) {
            return false;
        }
        return true;
    }

    public static int dfs(int x, int y) {
        if (x == h-1 && y == w-1) {
            return 1;
        }
        if (dp[x][y] >= 0) {
            return dp[x][y];
        }
        dp[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny)) {
                continue;
            }
            if (space[nx][ny] < space[x][y]) {
                dp[x][y] = (dp[x][y] + dfs(nx, ny)) % MOD;
            }
        }
        return dp[x][y];
    }
}
