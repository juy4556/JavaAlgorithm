package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 최솟값최댓값차이최소화하기 {
    static int N;
    static int[][] space;
    static StringTokenizer st;
    static int result = 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, new ArrayList<>(), new int[N]);
        System.out.println(result);
    }

    private static void dfs(int depth, List<Integer> list, int[] visited) {
        if (depth == N) {
            int max = -1;
            int min = 10001;
            for (int i = 0; i < N; i++) {
                max = Math.max(max, list.get(i));
                min = Math.min(min, list.get(i));
            }
            result = Math.min(result, max - min);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                list.add(space[depth][i]);
                dfs(depth + 1, list, visited);
                list.remove(list.size() - 1);
                visited[i] = 0;
            }
        }
    }
}
