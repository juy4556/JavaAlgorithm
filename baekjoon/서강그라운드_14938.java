package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서강그라운드_14938 {
    private static int n, m, r;
    private static int[] items;
    private static int[][] routes;
    private static StringTokenizer st;
    private static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n];
        routes = new int[n + 1][n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                routes[i][j] = 100000;
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            routes[a][b] = l;
            routes[b][a] = l;
        }


        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (routes[i][j] > routes[i][k] + routes[k][j]) {
                        routes[i][j] = routes[i][k] + routes[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (routes[i][j] <= m) {
                    sum += items[j - 1];
                }
            }
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
