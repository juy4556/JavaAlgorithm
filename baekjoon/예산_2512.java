package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 예산_2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requests = new int[N];
        Long total = 0L;
        Long result = 0L;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            total += requests[i];
        }
        Long M = Long.parseLong(br.readLine());
        Arrays.sort(requests);
        Long start = 1L;
        Long end = (long) requests[N - 1];
        int index = 0;
        if (total <= M) {
            System.out.println(end);
            return;
        }
        total = 0L;
        while (start <= end) {
            Long mid = (long) ((start + end) / 2);
            total = 0L;
            index = 0;
            for (int i = 0; i < N; i++) {
                if (requests[i] >= mid) {
                    index = i;
                    break;
                }
                total += requests[i];
            }
            total += (mid * (N - index));
            if (total <= M) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}
