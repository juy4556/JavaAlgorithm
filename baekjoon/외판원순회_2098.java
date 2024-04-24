package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회_2098 {
    static int N;
    static int[][] W;  // W[i][j]는 도시 i에서 도시 j로 가기 위한 비용
    static int[][] dp;  // dp[i][j]는 i번 행성 방문 후, j행성들을 모두 포함하는 최소 비용
    static final int MAX = 16_000_000;
    static int result = MAX;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // dp값을 MAX로 초기화하지 않는 이유는 기존에 실패한 경우, 미해결 경우까지 모두 다시 재귀를 돌게 되기 때문
            // 따라서 미해결은 -1, 실패는 MAX로 dp값을 설정
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = dfs(0, 1);
        System.out.println(result);
    }

    public static int dfs(int cur, int visited) {
        if (visited == (1 << N) - 1) {
            if (W[cur][0] == 0) return MAX;  // 길 없음
            return W[cur][0];
        }
        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }
        dp[cur][visited] = MAX;
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0 && W[cur][i] != 0) {
                dp[cur][visited] = Math.min(dp[cur][visited], W[cur][i] + dfs(i, visited | (1 << i)));
            }
        }
        return dp[cur][visited];
    }
}
