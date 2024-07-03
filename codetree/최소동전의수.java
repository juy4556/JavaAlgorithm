package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소동전의수 {
    static int N, M;
    static ArrayList<Integer> plus;
    static ArrayList<Integer> minus;
    static int[] visited;
    static int result = Integer.MAX_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        plus = new ArrayList<>();
        minus = new ArrayList<>();
        visited = new int[11001];  // 0 ~ 11000
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                plus.add(num);
            } else if (num < 0) {
                minus.add(num);
            }
        }
        bfs();
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void bfs() {
        Deque<Integer> sumQueue = new ArrayDeque<>();
        sumQueue.add(0);
        visited[0] = 1;

        while (!sumQueue.isEmpty()) {
            int sum = sumQueue.pollFirst();

            if (sum == M) {
                result = visited[sum] - 1;
                return;
            } else if (sum > M) {
                for (int n : minus) {
                    if (visited[sum + n] > 0) continue;
                    sumQueue.add(sum + n);
                    visited[sum + n] = visited[sum] + 1;
                }
            } else {
                for (int n : plus) {
                    if (visited[sum + n] > 0) continue;
                    sumQueue.add(sum + n);
                    visited[sum + n] = visited[sum] + 1;
                }
            }
        }
    }
}
