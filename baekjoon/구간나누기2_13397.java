package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간나누기2_13397 {
    static int N, M;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        if (N == 1) {
            System.out.println(0);
            return;
        }

        int start = 0;
        int end = 10000;
        while (start <= end) {
            int mid = (start + end) / 2;

            int sectionCount = 1;
            int maxNum = numbers[0];
            int minNum = numbers[0];

            for (int i = 1; i < N; i++) {
                maxNum = Math.max(maxNum, numbers[i]);
                minNum = Math.min(minNum, numbers[i]);
                if (maxNum - minNum > mid) {
                    sectionCount++;
                    maxNum = numbers[i];
                    minNum = numbers[i];
                }
            }

            if (sectionCount <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        System.out.println(start);
    }
}
