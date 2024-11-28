package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최솟값과최댓값_2357 {
    static int N, M;
    static StringTokenizer st;
    static int[] numbers;
    static int[] minTree;
    static int[] maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            numbers[i] = n;
        }

        initMinTree(1, 0, N - 1);
        initMaxTree(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = findMin(1, 0, N - 1, a - 1, b - 1);
            int max = findMax(1, 0, N - 1, a - 1, b - 1);
            System.out.println(min + " " + max);
        }

    }

    private static void initMinTree(int node, int start, int end) {
        if (start == end) {
            minTree[node] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;
        initMinTree(node * 2, start, mid);
        initMinTree(node * 2 + 1, mid + 1, end);
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
    }

    private static void initMaxTree(int node, int start, int end) {
        if (start == end) {
            maxTree[node] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;
        initMaxTree(node * 2, start, mid);
        initMaxTree(node * 2 + 1, mid + 1, end);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }

    private static int findMin(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start + end) / 2;
        int leftMin = findMin(node * 2, start, mid, left, right);
        int rightMin = findMin(node * 2 + 1, mid + 1, end, left, right);

        return Math.min(leftMin, rightMin);
    }

    private static int findMax(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MIN_VALUE;
        }

        if (left <= start && end <= right) {
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        int leftMax = findMax(node * 2, start, mid, left, right);
        int rightMax = findMax(node * 2 + 1, mid + 1, end, left, right);

        return Math.max(leftMax, rightMax);
    }

}
