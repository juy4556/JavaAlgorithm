package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이진트리_13325 {
    static int k;
    static int[] edges;
    static int[] dp;
    static int edgeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        edgeSize = (1 << (k + 1)) - 2;
        edges = new int[edgeSize + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= edgeSize; i++) {
            edges[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[edgeSize + 1];
        dfs(0);
        System.out.println(Arrays.stream(edges).sum());
    }

    private static int dfs(int cur) {
        int left = cur * 2 + 1;
        int right = left + 1;
        dp[cur] += edges[cur];
        if (left >= edgeSize) return dp[cur];
        int max = Math.max(dfs(left), dfs(right));

        if (dp[left] != max) {
            int diff = max - dp[left];
            edges[left] += diff;
            dp[left] = max;
        } else {
            int diff = max - dp[right];
            edges[right] += diff;
            dp[right] = max;
        }
        dp[cur] += max;
        return dp[cur];
    }
}
