package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 버블소트_세그먼트트리_1517 {
    static int N;
    static int[] A;
    static long[] tree;

    private static class Item {
        int value;
        int index;

        public Item(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        tree = new long[4 * N];
        List<Item> B = new ArrayList<>(N);
        long result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            B.add(new Item(A[i], i));
        }

        B.sort((a, b) -> (a.value == b.value ? Integer.compare(b.index, a.index) : Integer.compare(b.value, a.value)));

        buildTree(1, 0, N - 1);

        for (Item item : B) {
            int idx = item.index;

            result += query(1, idx + 1, N - 1, 0, N - 1);
            update(1, idx, 0, 0, N - 1);
        }

        System.out.println(result);
    }

    private static long buildTree(int node, int left, int right) {
        if (left == right) {
            return tree[node] = 1L;
        }

        int mid = left + (right - left) / 2;
        long leftValue = buildTree(node * 2, left, mid);
        long rightValue = buildTree(node * 2 + 1, mid + 1, right);

        return tree[node] = merge(leftValue, rightValue);
    }

    private static long query(int node, int start, int end, int treeLeft, int treeRight) {
        if (treeLeft > end || treeRight < start) {
            return 0;
        }
        if (start <= treeLeft && treeRight <= end) {
            return tree[node];
        }
        int mid = treeLeft + (treeRight - treeLeft) / 2;
        long leftValue = query(node * 2, start, end, treeLeft, mid);
        long rightValue = query(node * 2 + 1, start, end, mid + 1, treeRight);

        return merge(leftValue, rightValue);
    }

    private static long update(int node, int index, int newValue, int treeLeft, int treeRight) {
        if (index > treeRight || index < treeLeft) {
            return tree[node];
        }
        if (treeLeft == treeRight) {
            return tree[node] = newValue;
        }
        int mid = treeLeft + (treeRight - treeLeft) / 2;
        long leftValue = update(node * 2, index, newValue, treeLeft, mid);
        long rightValue = update(node * 2 + 1, index, newValue, mid + 1, treeRight);

        return tree[node] = merge(leftValue, rightValue);
    }

    private static long merge(long a, long b) {
        return a + b;
    }

}
