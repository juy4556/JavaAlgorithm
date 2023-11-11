package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.util.Arrays.stream;

public class 등수_구하기_1205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int result = -1;
        if (N == 0) {
            result = 1;
        } else if (N > 0) {
            long[] scores = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }
            scores = stream(scores).boxed()
                    .sorted(Comparator.reverseOrder())
                    .mapToLong(Long::longValue)
                    .toArray();
            for (int i = 0; i < N; i++) {
                if (scores[i] <= newScore) {
                    result = i + 1;
                    break;
                }
            }
            if (result == -1 && N < P) {
                result = N + 1;
            }
            if (N >= P && scores[P - 1] == newScore) {
                result = -1;
            }
        }
        System.out.println(result);

        br.close();
    }
}
