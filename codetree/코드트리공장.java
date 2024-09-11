package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 코드트리공장 {
    static int N, M, Q, T;
    static Deque<Integer>[] belts;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        belts = new Deque[M];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            belts[i] = new ArrayDeque();
        }

        int splitCount = N / M;

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            belts[i / splitCount].add(n);
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            switch (q) {
                case 1:
                    int a = Integer.parseInt(st.nextToken());
                    move1(a);
                    break;
                case 2:
                    a = Integer.parseInt(st.nextToken());
                    move2(a);
                    break;
                case 3:
                    a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    move3(a, b);
                    break;
            }
        }

        for (int i = 0; i < M; i++) {
            int sum = belts[i].stream().mapToInt(Integer::intValue).sum();
            if (sum >= T) {
                sb.append("Yes\n");
                continue;
            }
            sb.append("No\n");
        }

        System.out.println(sb.toString());
    }

    private static void move1(int a) {
        if (belts[a].isEmpty()) {
            return;
        }
        Integer first = belts[a].pollFirst();
        belts[a].addLast(first);
    }

    private static void move2(int a) {
        if (belts[a].isEmpty()) {
            return;
        }
        Integer last = belts[a].pollLast();
        belts[a].addFirst(last);
    }

    private static void move3(int a, int b) {
        while (!belts[a].isEmpty()) {
            belts[b].addFirst(belts[a].pollLast());
        }

    }
}
