package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 커피숍2 {
    static int N, Q;
    static StringTokenizer st;
    static int[] numbers;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        tree = new long[N * 4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        initTree(1, 0, N - 1);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long sum;
            if (x > y) {
                sum = query(1, 0, N - 1, y - 1, x - 1);
            } else {
                sum = query(1, 0, N - 1, x - 1, y - 1);
            }
            update(1, 0, N - 1, a - 1, b);

            System.out.println(sum);
        }
    }

    private static long initTree(int node, int start, int end) {
        if (start == end) {
            return tree[node] = (long)numbers[start];
        }

        int mid = start + (end - start) / 2;
        long left = initTree(node * 2, start, mid);
        long right = initTree(node * 2 + 1, mid + 1, end);
        return tree[node] = left + right;
    }

    private static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0L;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        long leftSum = query(node * 2, start, mid, left, right);
        long rightSum = query(node * 2 + 1, mid + 1, end, left, right);
        return leftSum + rightSum;
    }

    private static long update(int node, int start, int end, int index, int value) {
        if (index < start || index > end) {
            return tree[node];
        }
        if (start == end) {
            return tree[node] = (long)value;
        }

        int mid = start + (end - start) / 2;
        long left = update(node * 2, start, mid, index, value);
        long right = update(node * 2 + 1, mid + 1, end, index, value);
        return tree[node] = left + right;
    }
}
