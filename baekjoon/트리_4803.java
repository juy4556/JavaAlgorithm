package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 트리_4803 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            int[] parents = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (findParent(parents, a) == findParent(parents, b)) {
                    // cycle
                    unionParent(parents, a, 0);
                    unionParent(parents, b, 0);
                    continue;
                }

                unionParent(parents, a, b);
            }

            Set<Integer> treeRoots = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                parents[i] = findParent(parents, i);
                if (parents[i] > 0) {
                    treeRoots.add(parents[i]);
                }
            }

            if (treeRoots.isEmpty()) {
                System.out.printf("Case %d: No trees.\n", testCase);
            } else if (treeRoots.size() == 1) {
                System.out.printf("Case %d: There is one tree.\n", testCase);
            } else {
                System.out.printf("Case %d: A forest of %d trees.\n", testCase, treeRoots.size());
            }
            testCase++;
        }
    }

    private static int findParent(int[] parents, int x) {
        if (parents[x] != x) {
            return findParent(parents, parents[x]);
        }
        return parents[x];
    }

    private static void unionParent(int[] parents, int a, int b) {
        a = findParent(parents, a);
        b = findParent(parents, b);

        if (a <= b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }
}
