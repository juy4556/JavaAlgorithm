package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형의크기 {
    static int n, m;
    static int[][] space;
    static StringTokenizer st;
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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int size = 1;
                boolean flag = true;
                while (flag) {
                    for (int x = i; x < i + size; x++) {
                        for (int y = j; y < j + size; y++) {
                            if (!inRange(x, y) || space[x][y] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }
                    if (flag) {
                        size++;
                    }
                }
                result = Math.max(result, (size - 1) * (size - 1));
            }
        }
        System.out.println(result);
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
