package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class nxm표자르기2 {
    static int n, m;
    static int[][] space;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        space = new int[n][m];
        int result = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < (1 << (n * m)); i++) {
            result = Math.max(result, getSum(i));
        }

        System.out.println(result);
    }

    private static int getSum(int bit) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < m; j++) {
                int now = i * m + j;
                if ((bit & (1 << now)) == 0) {
                    rowSum *= 10;
                    rowSum += space[i][j];
                    continue;
                }
                sum += rowSum;
                rowSum = 0;
            }
            sum += rowSum;
        }

        for (int j = 0; j < m; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++) {
                int now = i * m + j;
                if ((bit & (1 << now)) != 0) {
                    colSum *= 10;
                    colSum += space[i][j];
                    continue;
                }
                sum += colSum;
                colSum = 0;
            }
            sum += colSum;
        }

        return sum;
    }
}
