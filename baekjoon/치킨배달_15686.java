package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 치킨배달_15686 {
    static int N, M;
    static int[][] space;
    static List<int[]> chicken;
    static StringTokenizer st;
    static int result = 5000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][N];
        chicken = new ArrayList<>(13);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
        dfs(0, new ArrayList<>());
        System.out.println(result);

    }

    private static void dfs(int begin, List<Integer> list) {
        if (list.size() == M) {
            int dist = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (space[i][j] == 1) {
                        int d = 100;
                        for (int chickenNum : list) {
                            int[] pos = chicken.get(chickenNum);
                            d = Math.min(d, Math.abs(pos[0] - i) + Math.abs(pos[1] - j));
                        }
                        dist += d;
                    }
                }
            }
            result = Math.min(result, dist);
            return;
        }
        for (int i = begin; i < chicken.size(); i++) {
            list.add(i);
            dfs(i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
