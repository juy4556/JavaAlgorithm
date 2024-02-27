package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 근손실_18429 {
    static int N;
    static int K;
    static int[] A;
    static final int weight = 500;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken()) - K;
        }
        dfs(visited, weight, 0);
        System.out.print(result);
    }

    public static void dfs(boolean[] visited, int now, int depth) {
        if (depth == N) {
            result++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (now + A[i] >= weight) {
                visited[i] = true;
                dfs(visited, now + A[i], depth + 1);
                visited[i] = false;
            }
        }
    }
}
