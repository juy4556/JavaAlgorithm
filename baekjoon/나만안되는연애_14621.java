package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 나만안되는연애_14621 {
    static int N, M;
    static String[] gender;
    static int[] parent;
    static StringTokenizer st;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        gender = br.readLine().split(" ");
        Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.get(0)));

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (gender[u - 1].equals(gender[v - 1])) {
                continue;
            }
            pq.add(List.of(d, u, v));
        }

        while (!pq.isEmpty()) {
            List<Integer> street = pq.poll();
            int d = street.get(0);
            int u = street.get(1);
            int v = street.get(2);
            if (findParent(u) == findParent(v)) {
                continue;
            }
            unionParent(u, v);
            result += d;
        }

        Set<Integer> parentSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            parent[i] = findParent(i);
            parentSet.add(parent[i]);
        }

        if (parentSet.size() == 1) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    private static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
