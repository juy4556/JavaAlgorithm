package codetree;

import java.io.*;
import java.util.*;

public class 코드트리코딩대회 {
    static int N, M;
    static Integer[] difficulty;
    static int result = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        difficulty = new Integer[N];
        input = br.readLine().split(" ");
        boolean canSolve = true;

        for (int i = 0; i < N; i++) {
            difficulty[i] = Integer.parseInt(input[i]);
            if (difficulty[i] > M) {
                canSolve = false;
            }
        }

        if (canSolve) {
            dfs(0, new ArrayList<>());
        }

        if (result == 1_000_000_000) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void dfs(int index, List<Integer> list) {
        if (index == N) {
            result = Math.min(result, list.size());
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) + difficulty[index] <= M) {
                list.set(i, list.get(i) + difficulty[index]);
                dfs(index + 1, list);
                list.set(i, list.get(i) - difficulty[index]);
            }
        }

        list.add(difficulty[index]);
        dfs(index + 1, list);
        list.remove(list.size() - 1);
    }
    /*
    2 (2)
    3 (5), (2, 3)
    7 (5, 7), (9, 3), (2, 10), (2, 3, 7)
    8 (5,7,8), (9,3,8), (10, 10), (10, 3, 7), (2, 10, 8), (2, 3, 7, 8)
    이런식으로 dfs와 dp를 이용해서 풀어야 함.
     */


}
