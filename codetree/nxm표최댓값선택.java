package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class nxm표최댓값선택 {
    static int n, m;
    static int[][] space;
    static int[][] dp;
    static StringTokenizer st;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        space = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new ArrayList<>(), 0);
        System.out.println(result);
    }

    private static void dfs(ArrayList<Integer> list, int begin) {
        if (list.size() == 3) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int max = 0;
                for (int j = 0; j < m; j++) {
                    if (list.contains(j)) continue;
                    max = Math.max(max, space[i][j]);
                }
                sum += max;
            }
            result = Math.max(result, sum);
        }

        for (int i = begin; i < m; i++) {
            list.add(i);
            dfs(list, i + 1);
            list.remove(list.size() - 1);

        }
    }
}
