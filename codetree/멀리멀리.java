package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 멀리멀리 {
    static int N, M;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[N - 1] - arr[0];
        int max = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 1;
            int prev = arr[0];

            for (int i = 1; i < N; i++) {
                if (arr[i] - prev >= mid) {
                    count++;
                    prev = arr[i];
                }
            }

            if (count >= M) {
                max = Math.max(max, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(max);
    }
}
