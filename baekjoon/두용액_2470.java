package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 두용액_2470 {
    static int N;
    static int[] liquids;
    static int minSum = Integer.MAX_VALUE;
    static int[] result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liquids = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquids);
        for (int i = 0; i < N; i++) {
            int target = -liquids[i];
            binarySearch(target, i, i + 1, N - 1);
            if (minSum == 0) {
                break;
            }
        }
        System.out.printf("%d %d", result[0], result[1]);
    }

    public static void binarySearch(int target, int now, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = liquids[now] + liquids[mid];
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                result[0] = liquids[now];
                result[1] = liquids[mid];
            }
            if (liquids[mid] >= target) {
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }}
}
