package codetree;

import java.util.*;
import java.io.*;

public class nxm표자르기 {
    static int n, m;
    static StringTokenizer st;
    static int[][] space;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        space = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < (1 << (n * m)); k++) {
            int sum = getSum(k);
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }

    private static int getSum(int k) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int row = 0;
            for (int j = 0; j < m; j++) {
                int pos = 1 << (i * m + j);
                if ((pos & k) != 0) {
                    row = row * 10 + space[i][j];
                } else {
                    sum += row;
                    row = 0;
                }
            }
            sum += row;
        }

        for (int j = 0; j < m; j++) {
            int col = 0;
            for (int i = 0; i < n; i++) {
                int pos = 1 << (i * m + j);
                if ((pos & k) == 0) {
                    col = col * 10 + space[i][j];
                } else {
                    sum += col;
                    col = 0;
                }
            }
            sum += col;
        }
        return sum;
    }
}