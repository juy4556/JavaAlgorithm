package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 흩날리는시험지속에서내평점이느껴진거야_17951 {
    static int N, K;
    static int[] tests;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tests = new int[N];
        st = new StringTokenizer(br.readLine());
        int testSum = 0;

        for (int i = 0; i < N; i++) {
            tests[i] = Integer.parseInt(st.nextToken());
            testSum += tests[i];
        }

        int left = 0;
        int right = testSum;
        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            int groupCount = 0;

            for (int i = 0; i < N; i++) {
                sum += tests[i];
                if (sum >= mid) {
                    sum = 0;
                    groupCount++;
                }
            }

            if (groupCount < K) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left - 1);
    }
}
