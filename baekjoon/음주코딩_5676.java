package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 음주코딩_5676 {
    static int N, K;
    static int[] X;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer;
        while (true) {
            answer = new StringBuilder();
            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());
                X = new int[N];
                tree = new int[N * 4];

                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    X[i] = Integer.parseInt(st.nextToken());
                }

                buildTree(X, tree, 1, 0, N - 1);

                for (int i = 0; i < K; i++) {
                    st = new StringTokenizer(br.readLine());
                    String command = st.nextToken();
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    if (command.equals("C")) {
                        updateTree(tree, a - 1, b, 1, 0, N - 1);
                    }
                    if (command.equals("P")) {
                        int result = query(tree, a - 1, b - 1, 1, 0, N - 1);
                        if (result > 0) {
                            answer.append('+');
                        } else if (result == 0) {
                            answer.append('0');
                        } else {
                            answer.append('-');
                        }
                    }
                }
                System.out.println(answer);
            } catch (Exception e) {
                return;
            }
        }
    }

    private static int buildTree(int[] X, int[] tree, int node, int left, int right) {
        if (left == right) {
            return tree[node] = X[left];
        }

        int mid = left + (right - left) / 2;
        int leftValue = buildTree(X, tree, node * 2, left, mid);
        int rightValue = buildTree(X, tree, node * 2 + 1, mid + 1, right);

        return tree[node] = merge(leftValue, rightValue);
    }

    private static int merge(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        } else if ((a > 0 && b > 0) || (a < 0 && b < 0)) {
            return 1;
        } else {
            return -1;
        }
    }

    /*
        left, right: 값을 구할 구간
        node: 트리 노드 번호
        treeLeft, treeRight: 해당 노드를 구성하는 하위 트리 범위
     */
    private static int query(int[] tree, int left, int right, int node, int treeLeft, int treeRight) {
        if (treeLeft > right || treeRight < left) {
            return 1;
        }
        if (left <= treeLeft && treeRight <= right) {
            return tree[node];
        }

        int mid = treeLeft + (treeRight - treeLeft) / 2;
        int leftValue = query(tree, left, right, node * 2, treeLeft, mid);
        int rightValue = query(tree, left, right, node * 2 + 1, mid + 1, treeRight);

        return merge(leftValue, rightValue);
    }

    private static int updateTree(int[] tree, int updateIndex, int newValue, int node, int treeLeft, int treeRight) {
        if (updateIndex < treeLeft || updateIndex > treeRight) {
            return tree[node];
        }

        if (treeLeft == treeRight) {
            return tree[node] = newValue;
        }

        int mid = treeLeft + (treeRight - treeLeft) / 2;
        int leftValue = updateTree(tree, updateIndex, newValue, node * 2, treeLeft, mid);
        int rightValue = updateTree(tree, updateIndex, newValue, node * 2 + 1, mid + 1, treeRight);

        return tree[node] = merge(leftValue, rightValue);
    }
}
